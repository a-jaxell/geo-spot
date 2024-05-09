package com.laboration2.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableMethodSecurity(
        jsr250Enabled = true
)
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
         http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.GET, "/error").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/locations").permitAll()
                        .requestMatchers("/api/categories/**").permitAll()
                        .requestMatchers("/api/locations/**").authenticated()
                        .anyRequest().authenticated()
                )
                 .httpBasic(withDefaults())
                 .csrf(AbstractHttpConfigurer::disable)
                 .headers( headers -> headers
                         .httpStrictTransportSecurity(Customizer.withDefaults())
                         .xssProtection(Customizer.withDefaults())
                         .contentSecurityPolicy(csp -> csp.policyDirectives("default-src 'self'"))
                 );
        return http.build();
    }
    @Bean
    public UserDetailsService userDetailsService() {
        // Create an encoder with default settings
        PasswordEncoder encoder = passwordEncoder();

        // Create an in-memory user store with two users
        UserDetails admin = User.builder()
                .username("admin")
                .password(encoder.encode("adminpass"))
                .roles("ADMIN")  // Role ADMIN
                .build();

        UserDetails user = User.builder()
                .username("user")
                .password(encoder.encode("userpass"))
                .roles("USER")  // Role USER
                .build();

        // Return a manager for the in-memory store
        return new InMemoryUserDetailsManager(admin, user);
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
