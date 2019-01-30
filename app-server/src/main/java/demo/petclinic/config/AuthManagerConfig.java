package demo.petclinic.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableGlobalAuthentication
public class AuthManagerConfig {
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Autowired
    public void initAuthManager(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("joe").password(encrypt("foo")).roles("OWNER", "ADMIN")
                .and().withUser("sam").password(encrypt("bar")).roles("OWNER");
    }

    private String encrypt(String password) {
        return "{bcrypt}" + encoder.encode(password);
    }
}
