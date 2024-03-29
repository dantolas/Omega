package com.kuta.app.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * AppConfing
 */
@Configuration
public class AppConfig {

    @Bean
    public Gson gson(){
        return new GsonBuilder().setPrettyPrinting().create();
    }

    @Bean
    public PasswordEncoder encoder(){
        return new BCryptPasswordEncoder(16);
    }
    
}
