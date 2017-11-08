package com.dinner;

import com.dinner.model.security.AuthenticationFacade;
import com.dinner.model.security.AuthenticationFacadeImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class DinnerApplication {


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(4);
    }

    @Bean
    @Lazy
    public AuthenticationFacade authenticationFacade() {
        return new AuthenticationFacadeImpl();
    }

    public static void main(String[] args) {
        SpringApplication.run(DinnerApplication.class, args);
    }
}
