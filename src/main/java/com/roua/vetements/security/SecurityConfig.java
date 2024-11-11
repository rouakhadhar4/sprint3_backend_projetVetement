package com.roua.vetements.security;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import jakarta.servlet.http.HttpServletRequest;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled= true)
public class SecurityConfig {
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	    http.csrf().disable()
	        .sessionManagement()
	            .sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
	        .cors().configurationSource(new CorsConfigurationSource() {
	            @Override
	            public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
	                CorsConfiguration config = new CorsConfiguration();
	                config.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
	                config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
	                config.setAllowCredentials(true);
	                config.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type", "X-Requested-With", "Accept", "Origin"));
	                config.setExposedHeaders(Arrays.asList("Authorization"));
	                config.setMaxAge(3600L);
	                return config;
	            }
	        }).and()
	        .authorizeHttpRequests()
	            .anyRequest().permitAll()  // Permet l'accès à toutes les requêtes sans restriction
	        .and()
	        .addFilterBefore(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);

	    return http.build();
	}

}



	


