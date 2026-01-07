package com.walterjwhite.examples.spring.security;

import com.walterjwhite.examples.spring.bank.BankUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class AdminSecurityConfig {
  private final BankUserDetailsService userDetailsService;

  @Value("${app.admin.enabled:false}")
  private boolean adminEnabled;

  @Value("${app.admin.username:admin}")
  private String username;

  @Value("${app.admin.password:changeit}")
  private String password;

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public InMemoryUserDetailsManager userDetailsService(PasswordEncoder passwordEncoder) {
    if (!adminEnabled) {
      return new InMemoryUserDetailsManager();
    }

    UserDetails user =
        User.withUsername(username)
            .password(passwordEncoder.encode(password))
            .roles("ADMIN")
            .build();
    return new InMemoryUserDetailsManager(user);
  }

  @Bean
  public SecurityFilterChain filterChain(final HttpSecurity http) throws Exception {


    return http.authorizeHttpRequests(authorize -> authorize.anyRequest().permitAll())
        .csrf(AbstractHttpConfigurer::disable)
        .httpBasic(Customizer.withDefaults())
        .userDetailsService(userDetailsService)
        .build();
  }
}
