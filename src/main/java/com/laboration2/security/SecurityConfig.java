package com.laboration2.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtDecoders;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    @Value("${JWT_ISSUER_URI}")
    private String issuerUri;
    @Bean
    protected JwtDecoder jwtDecoder(){
        return JwtDecoders.fromIssuerLocation(issuerUri);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        JwtAuthenticationConverter jwtConverter = new JwtAuthenticationConverter();
        jwtConverter.setJwtGrantedAuthoritiesConverter(new CustomJwtConverter());


        return http
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(jwt -> jwt
                                .jwtAuthenticationConverter(jwtConverter)
                        )
                )
                //Customizer.withDefaults()
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.GET, "/api/categories").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/categories/*").permitAll()
                        .requestMatchers(HttpMethod.POST,"/api/categories").hasAuthority("ADMIN")

                        .requestMatchers(HttpMethod.GET, "/api/locations").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/locations").authenticated()
                        .requestMatchers(HttpMethod.PATCH, "/api/locations").authenticated()
                        .requestMatchers(HttpMethod.DELETE, "/api/locations").authenticated()

                        .requestMatchers(HttpMethod.GET, "/api/locations/*").authenticated()
                        .requestMatchers(HttpMethod.DELETE, "/api/locations/nearby").permitAll()

                        .anyRequest().denyAll()
                )
                .build();
    }
}
