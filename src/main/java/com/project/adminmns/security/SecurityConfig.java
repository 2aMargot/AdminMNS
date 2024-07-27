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
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

/**
 * Spring Security configuration class that sets up authentication, authorization,
 * and CORS settings for the application.
 * <p>
 * This configuration disables CSRF protection, enables CORS with specific settings,
 * and sets up JWT-based stateless authentication.
 * </p>
 */
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

    /**
     * Provides a custom {@link AuthenticationProvider} for authentication using
     * {@link DaoAuthenticationProvider} with a user details service and password encoder.
     *
     * @return The configured {@link AuthenticationProvider}.
     */
    @Bean
    public AuthenticationProvider authenticationProvider () {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(appUserDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(bCryptPasswordEncoder);
        return daoAuthenticationProvider;
    }

    /**
     * Configures HTTP security settings, including disabling CSRF protection,
     * setting up CORS, session management, and adding a JWT filter.
     *
     * @param http The {@link HttpSecurity} object used to configure HTTP security.
     * @return The configured {@link SecurityFilterChain}.
     * @throws Exception If an error occurs while configuring HTTP security.
     */
    @Bean
    public SecurityFilterChain httpSecurity (HttpSecurity http) throws Exception {

        return http
                .csrf(config -> config.disable())
                .cors(config -> config.configurationSource(corsConfigurationSource()))
                .sessionManagement(config -> config.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    /**
     * Provides CORS configuration for the application.
     *
     * @return The configured {@link CorsConfigurationSource}.
     */
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedOrigins(List.of("*"));
        corsConfiguration.setAllowedMethods(List.of("GET", "POST", "DELETE", "PUT"));
        corsConfiguration.setAllowedHeaders(List.of("*"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);
        return source;
    }

}