package demo.petclinic.auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.approval.InMemoryApprovalStore;

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
        endpoints.approvalStore(new InMemoryApprovalStore());
    }
}
