package com.example.jaiBackend2.Config;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.jaiBackend2.Entity.User;
import com.example.jaiBackend2.Repository.UserRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @SuppressWarnings("removal")
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf().disable()
                .authorizeHttpRequests()
                    .requestMatchers("/api/auth/**", "/login", "/logout").permitAll()
                    .anyRequest().authenticated()
                .and()
                .formLogin().loginProcessingUrl("/login").permitAll()
                .and()
                .logout().permitAll()
                .and()
                .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .and()
                .build();
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepo) {
        return username -> {
            User user = userRepo.findByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));
            return new org.springframework.security.core.userdetails.User(
                    user.getUsername(), user.getPassword(), new ArrayList<>());
        };
    }

   

}
