package com.example.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Permite todas as rotas
                .allowedOrigins("http://127.0.0.1:5500") // Adicione o URL do seu front-end
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Permite os métodos que você deseja
                .allowedHeaders("*") // Permite todos os cabeçalhos
                .allowCredentials(true); // Permite o uso de credenciais (como cookies)
    }
}
