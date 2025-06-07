package org.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

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
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/users", "/api/users/dashboard/delete/**", "/api/trajets").permitAll() // hedhi ala khater namlou fi ops ta3 delete w  cryptage ta3 pass  w user is unauthenticated
                        .anyRequest().authenticated()
                ).httpBasic(withDefaults());
        return http.build();
    }
}