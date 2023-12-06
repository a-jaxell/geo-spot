package com.laboration2.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .httpBasic(Customizer.withDefaults())
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.GET, "/api/categories").permitAll()
                        .requestMatchers(HttpMethod.POST,"/api/categories").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/categories/*").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/locations").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/locations").permitAll()
                        .requestMatchers(HttpMethod.PATCH, "/api/locations").hasRole("USER")
                        .requestMatchers(HttpMethod.DELETE, "/api/locations").hasRole("USER")
                        .requestMatchers(HttpMethod.GET, "/api/locations/*").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/api/locations/nearby").permitAll()
                        .anyRequest().permitAll()
                )
                .build();
    }
}
