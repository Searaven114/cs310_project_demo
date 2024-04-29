package com.sabancinuiv.cs310_project_demo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig {

    /**
    * Filter chain configuration happens here, additionally formLogin is provided by Spring Security 5 as HTML form
    * in the next step, we will provide a custom frontend for form data processing
    *
    **/
    @Bean
    protected SecurityFilterChain configure (HttpSecurity http) throws Exception{

        return http
                .csrf(csrf->csrf.disable())
                .authorizeHttpRequests(auth -> {

                    auth.requestMatchers("/api/v1/user/admin/**").hasRole("ADMIN");
                    auth.requestMatchers("/api/v1/entries/admin/**").hasRole("ADMIN");

                    //auth.requestMatchers("/api/v1/user/login", "/api/v1/user/logout").permitAll();
                    auth.requestMatchers("/api/v1/user").permitAll(); //Allows register via POST

                    auth.requestMatchers("/api/v1/user/**").permitAll();
                    auth.requestMatchers("/api/v1/entries/**").permitAll();

                    auth.anyRequest().authenticated();

                })
                .formLogin(Customizer.withDefaults())
                .build();

    }

    /**
    *  Passwords will be encrypted via BCrypt
    *
    **/
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }



}































