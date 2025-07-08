package org.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;

import java.util.List;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
//This tells Spring how to encode passwords, and it will inject this PasswordEncoder bean wherever you use it kima f UserServiceImpl.
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.cors(cors -> cors.configurationSource(request -> {
            CorsConfiguration config = new CorsConfiguration();
            config.setAllowedOrigins(List.of("http://localhost:4200"));
            config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
            config.setAllowedHeaders(List.of("*"));
            config.setAllowCredentials(true);
            return config;
        }));

        http.csrf(csrf -> csrf.disable());

        // all reauests are allowed without auth
        http.authorizeHttpRequests(auth -> auth
                .anyRequest().permitAll()
        );

        // old configuration #### no delete ###
//    http.csrf(csrf -> csrf.disable())
//        .authorizeHttpRequests(auth -> auth
//            .requestMatchers(
//                "/api/dashboard/",
//                "/api/dashboard/**",
//                "/api/dashboard/deleteUser/**",
//                "/api/countUsers",
//                "/api/dashboard/user",
//                "/api/dashboard/user/**",
//                "/addVehiculeToUser/**",
//                "/api/trajets/**",
//                "/api/trajets/*/reserver",
//                "/api/vehicules/**",
//                "/api/vehicules/user/**",
//                "/api/trajets",
//                "/api/trajets/*/vehicules/*"
//            ).permitAll()
//            .anyRequest().authenticated()
//        );

        http.httpBasic(withDefaults());

        return http.build();
    }
}