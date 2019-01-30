package demo.petclinic.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;
import java.util.Collections;

@Configuration
public class WebSecurityConfig {
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        var config = new CorsConfiguration();
        config.setAllowedOrigins(Collections.singletonList("*"));
        config.setAllowedMethods(Arrays.asList("*"));
        config.setAllowCredentials(true);
        config.setAllowedHeaders(Arrays.asList("Content-Type", "Cache-Control", "Authorization"));

        var configSource = new UrlBasedCorsConfigurationSource();
        configSource.registerCorsConfiguration("/**", config);
        return configSource;
    }

    @Bean
    public FilterRegistrationBean<CorsFilter> customCorsFilter() {
        var corsFilter = new FilterRegistrationBean<>(new CorsFilter(corsConfigurationSource()));
        corsFilter.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return corsFilter;
    }
}
