package co.ucentral.edu.emailSender.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // 🚨 Desactiva CSRF
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/email/send").permitAll() // Permite acceso a la API de email
                        .anyRequest().authenticated()
                );

        return http.build();
    }
}
