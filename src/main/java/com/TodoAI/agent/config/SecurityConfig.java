package com.TodoAI.agent.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import com.TodoAI.agent.service.UserService;

@Configuration
public class SecurityConfig {

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    // http
    // .csrf(csrf -> csrf.disable()) // disable for development (use CSRF later)
    // .authorizeHttpRequests(auth -> auth
    // .requestMatchers("/user/register", "/user/login").permitAll()
    // .anyRequest().authenticated())
    // .formLogin(form -> form
    // .loginProcessingUrl("/login/")
    // .defaultSuccessUrl("/task/all", true)
    // .permitAll())
    // .logout(logout -> logout.permitAll());

    // http
    // .csrf(csrf -> csrf.disable())
    // .authorizeHttpRequests(auth -> auth
    // .requestMatchers("/login/**", "/user/register").permitAll()
    // .anyRequest().authenticated())
    // .httpBasic(Customizer.withDefaults()) // temporary: allows testing with curl
    // .formLogin(form -> form.disable())
    // .logout(logout -> logout.disable());

    http
        .csrf(csrf -> csrf.disable()) // for API testing only, enable in prod
        .authorizeHttpRequests(auth -> auth
            .requestMatchers("/login", "/register").permitAll()
            .anyRequest().authenticated())
        .formLogin(Customizer.withDefaults()) // enables /login endpoint
        .logout(Customizer.withDefaults())
        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED));
    return http.build();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public DaoAuthenticationProvider authProvider(UserService userDetailsService, PasswordEncoder encoder) {
    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
    authProvider.setUserDetailsService(userDetailsService);
    authProvider.setPasswordEncoder(encoder);
    return authProvider;
  }
}

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import
// org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.web.SecurityFilterChain;
//
// @Configuration
// public class SecurityConfig {
//
// @Bean
// public SecurityFilterChain securityFilterChain(HttpSecurity http) throws
// Exception {
// http
// .csrf().disable() // disable CSRF for simplicity in dev/curl
// .authorizeHttpRequests(auth -> auth
// .anyRequest().permitAll() // allow everything
// );
// return http.build();
// }
// }
