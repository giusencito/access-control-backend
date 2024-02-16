package com.dasser.ControlAccess.backend.configuration;

import com.dasser.ControlAccess.backend.security.jwt.jwtEntryPoint;
import com.dasser.ControlAccess.backend.security.jwt.jwtTokenFilter;
import com.dasser.ControlAccess.backend.security.service.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

@EnableWebSecurity
@EnableMethodSecurity
@Configuration
public class WebSecurityConfig{
    @Autowired
    UserDetailsImpl userDetails;

    @Autowired
    jwtEntryPoint entryPoint;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    jwtTokenFilter tokenFilter;


    AuthenticationManager authenticationManager;


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder builder = http.getSharedObject(AuthenticationManagerBuilder.class);
        builder.userDetailsService(userDetails).passwordEncoder(passwordEncoder);
        authenticationManager = builder.build();
        http.authenticationManager(authenticationManager);

        http.csrf(csrf -> csrf.disable());

        http.cors(cors-> cors.configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues()));
        http.sessionManagement(sessionManagement-> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http.authorizeHttpRequests(authorizeHttpRequests->authorizeHttpRequests.requestMatchers(
                "/auth/",
                "/email-password/",
                "/myapi/",
                "/v2/api-docs/",
                "/**"

        ).permitAll().anyRequest().authenticated());
        http.exceptionHandling(exceptionHandling->exceptionHandling.authenticationEntryPoint(entryPoint));
        http.addFilterBefore(tokenFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }







}








