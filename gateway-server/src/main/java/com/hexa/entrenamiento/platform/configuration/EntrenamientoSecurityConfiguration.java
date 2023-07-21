package com.hexa.entrenamiento.platform.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;



@EnableWebSecurity
public class EntrenamientoSecurityConfiguration extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable().cors().and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/**").permitAll();
    }


    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.addAllowedOrigin("*");
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.addAllowedMethod("OPTIONS");
        corsConfiguration.addAllowedMethod("GET");
        corsConfiguration.addAllowedMethod("PUT");
        corsConfiguration.addAllowedMethod("POST");
        corsConfiguration.addAllowedMethod("DELETE");
        corsConfiguration.addAllowedMethod("PATCH");
        corsConfiguration.setMaxAge(Long.MAX_VALUE);
        source.registerCorsConfiguration("/**", corsConfiguration);
        return source;
    }
}
