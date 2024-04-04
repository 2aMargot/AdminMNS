package com.project.adminmns.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    AppUserDetailsService appUserDetailsService;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    JwtFilter jwtFilter;

    // Configuration de l'authentification
    @Bean
    public AuthenticationProvider authenticationProvider () {

        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(appUserDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(bCryptPasswordEncoder);

        return daoAuthenticationProvider;
    }

    // configuration des permisisons
    @Bean
    public SecurityFilterChain httpSecurity (HttpSecurity http) throws Exception {

        return http
                .authorizeHttpRequests(
                        config -> config.requestMatchers("/connexion","/inscription").permitAll()
                                .anyRequest().authenticated())
                .csrf(config -> config.disable())
                .sessionManagement(config -> config.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

}
