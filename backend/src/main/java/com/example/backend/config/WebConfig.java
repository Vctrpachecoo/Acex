package com.example.backend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


// Configuração do cors
@Configuration
public class WebConfig implements WebMvcConfigurer {

    // Liberando as requisições e as portas
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Permite todas as rotas
                .allowedOrigins("http://localhost:4200/") // Adicione o URL do seu front-end
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Apenas um metodo permitido
                .allowedHeaders("*") // Permite todos os cabeçalhos
                .allowCredentials(true); // Permite o uso de credenciais (como cookies)
    }
}
