package com.agileapp.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) //ne koristi se CSRF zastita
                .authorizeHttpRequests(auth -> auth //inofmacije o tome za koji request je neophodna prijava a za koji ne
                        .requestMatchers("/auth/login").permitAll()
                        .requestMatchers("/projects/**").permitAll()//ovaj endpoint je dostupan svima
                        .requestMatchers("/users/**").permitAll()
                        .anyRequest().authenticated() //svaki drugi request mora biti autentifikovan
                )
                .httpBasic(Customizer.withDefaults());

        return http.build(); //ovde se cela konfiguracija završava i pretvara u SecurityFilterChain koji Spring koristi.
    }

}
