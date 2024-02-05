package com.example.LogisticCompany.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public FilterRegistrationBean corsConfigurer(){
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        CorsConfiguration cors = new CorsConfiguration();
        cors.addAllowedHeader(CorsConfiguration.ALL);
        cors.addAllowedOrigin(CorsConfiguration.ALL);
        cors.addAllowedMethod(CorsConfiguration.ALL);

        source.registerCorsConfiguration("/**", cors);

        FilterRegistrationBean bean  = new FilterRegistrationBean(new CorsFilter(source));
        bean.setOrder(0);

        return bean;
    }
}