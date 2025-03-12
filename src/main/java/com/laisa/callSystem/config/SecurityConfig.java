package com.laisa.callSystem.config;

import java.util.Arrays;

import com.laisa.callSystem.security.JWTAuthenticationFilter;
import com.laisa.callSystem.security.JWTAuthorizationFilter;
import com.laisa.callSystem.security.JWTUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;


@EnableWebSecurity
@Configuration
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    private final JWTUtil jwtUtil;
    private final UserDetailsService userDetailsService;

    public SecurityConfig(JWTUtil jwtUtil, UserDetailsService userDetailsService) {
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

    // CONFIGURAÇÃO DO GERENCIADOR DE AUTENTICAÇÃO
    @Bean
    AuthenticationManager authenticationManager(HttpSecurity httpSecurity, BCryptPasswordEncoder passwordEncoder) throws Exception {
        return httpSecurity.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder)
                .and()
                .build();
    }

    // CONFIGURAÇÃO DA CADEIA DE FILTROS DE SEGURANÇA
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity, AuthenticationManager authenticationManager) throws Exception {

        return httpSecurity
                .csrf(csrf -> csrf.disable()) // DESATIVA CSRF (RECOMENDADO PARA APIS RESTFUL)
                .cors(cors -> cors.configurationSource(corsConfigurationSource())) // ATIVA CORS COM CONFIGURAÇÃO PERSONALIZADA
                .addFilterBefore(new JWTAuthenticationFilter(authenticationManager, jwtUtil),
                        UsernamePasswordAuthenticationFilter.class) // FILTRO DE AUTENTICAÇÃO JWT
                .addFilterBefore(new JWTAuthorizationFilter(authenticationManager, jwtUtil, userDetailsService),
                        UsernamePasswordAuthenticationFilter.class) // FILTRO DE AUTORIZAÇÃO JWT
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/login/**").permitAll() // PERMITE ACESSO SEM AUTENTICAÇÃO AO LOGIN
                        .anyRequest().authenticated() // EXIGE AUTENTICAÇÃO PARA OUTROS ENDPOINTS
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // NÃO ARMAZENA A SESSÃO DO USUÁRIO
                .build();
    }

    // CONFIGURAÇÃO DE CORS
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
        configuration.setAllowedMethods(Arrays.asList("POST", "GET", "DELETE", "OPTIONS", "PUT"));
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type", "Accept", "Access-Control-Allow-Headers"));
        configuration.setAllowCredentials(true);
        configuration.addExposedHeader("Authorization");

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    // CONFIGURAÇÃO DO ENCODER DE SENHAS
    @Bean
    BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
