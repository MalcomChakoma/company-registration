package com.company.registration.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.*;

import java.util.List;

@Configuration
public class CorsConfig {

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {

        CorsConfiguration config = new CorsConfiguration();

        // ✅ Allow ALL origins (Spring Boot 3 way)
        config.setAllowedOriginPatterns(List.of("*"));

        // ✅ Allow all methods
        config.setAllowedMethods(List.of("*"));

        // ✅ Allow all headers
        config.setAllowedHeaders(List.of("*"));

        // ⚠️ Must be FALSE when using "*"
        config.setAllowCredentials(false);

        UrlBasedCorsConfigurationSource source =
                new UrlBasedCorsConfigurationSource();

        source.registerCorsConfiguration("/**", config);

        return source;
    }
}