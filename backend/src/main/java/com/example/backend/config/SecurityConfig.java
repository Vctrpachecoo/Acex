package com.example.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

// Configuração da dependência de Security
@Configuration
public class SecurityConfig {

    // Evita o erro de forbidden
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Desabilita o CSRF
                .authorizeHttpRequests(authz -> authz
                        // liberando os endpoints que podem ser acessados
                        .requestMatchers("/users/registerUsers", "/users/status", "/users/login").permitAll()
                        .anyRequest().authenticated() // Exige autenticação para outras requisições
                );
        return http.build();
    }
}
