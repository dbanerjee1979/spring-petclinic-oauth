package demo.petclinic.auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.approval.InMemoryApprovalStore;
import org.springframework.security.oauth2.provider.token.AccessTokenConverter;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
public class AuthServerConfig implements AuthorizationServerConfigurer {
    @Autowired
    private AuthManagerConfig auth;

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("trusted-client").secret(auth.encrypt("trusted-client-secret"))
                .scopes("owner:read", "owner:write", "vets:read")
                .autoApprove(true)
                .authorizedGrantTypes("implicit", "authorization_code", "password", "refresh_token")
                .redirectUris("http://localhost:4200", "http://localhost:8080/pet-clinic/login")
            .and().withClient("public-client").secret("{noop}")
                .scopes("owner:read", "owner:write", "vets:read")
                .autoApprove(true)
                .authorizedGrantTypes("implicit", "authorization_code")
                .redirectUris("http://localhost:4200");
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.approvalStore(new InMemoryApprovalStore())
                 .accessTokenConverter(tokenConverter())
                 .tokenStore(tokenStore())
                 .tokenServices(tokenServices());
    }

    @Bean
    public DefaultTokenServices tokenServices() {
        var tokenServices = new DefaultTokenServices();
        tokenServices.setTokenEnhancer(tokenConverter());
        tokenServices.setTokenStore(tokenStore());
        return tokenServices;
    }

    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(tokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter tokenConverter() {
        var tokenConverter = new JwtAccessTokenConverter();
        tokenConverter.setSigningKey("secret-key");
        return tokenConverter;
    }
}
