package com.project.websvc;

import javax.management.relation.Role;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.project.websvc.user.UserRole;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
  // @Bean
  // SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
  // http.authorizeHttpRequests((authorizeHttpRequests) -> authorizeHttpRequests
  // .requestMatchers(new AntPathRequestMatcher("/**")).permitAll())
  // .csrf((csrf) -> csrf
  // .ignoringRequestMatchers(new AntPathRequestMatcher("/h2-console/**")))
  // .headers((headers) -> headers
  // .addHeaderWriter(new
  // XFrameOptionsHeaderWriter(XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN)))
  // .formLogin((formLogin) ->
  // formLogin.loginPage("/user/login").defaultSuccessUrl("/"))
  // .logout((logout) -> logout
  // .logoutRequestMatcher(new AntPathRequestMatcher("/user/logout"))
  // .logoutSuccessUrl("/")
  // .invalidateHttpSession(true));
  // return http.build();
  // }

  @Bean
  SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.authorizeHttpRequests((authorizeHttpRequests) -> authorizeHttpRequests
        .requestMatchers(new AntPathRequestMatcher("/bbs"), 
        new AntPathRequestMatcher("/user/login"), 
        new AntPathRequestMatcher("/user/signup"), 
        new AntPathRequestMatcher("/*.css"),
        new AntPathRequestMatcher("/*.js")).permitAll()
        .requestMatchers(new AntPathRequestMatcher("/question/**")).hasRole(UserRole.USER.name()).anyRequest().authenticated())
        .csrf((csrf) -> csrf
            .ignoringRequestMatchers(new AntPathRequestMatcher("/h2-console/**")))
        .headers((headers) -> headers
            .addHeaderWriter(new XFrameOptionsHeaderWriter(XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN)))
        .formLogin((formLogin) -> formLogin.loginPage("/user/login").defaultSuccessUrl("/question/list"))
        .logout((logout) -> logout
            .logoutRequestMatcher(new AntPathRequestMatcher("/user/logout"))
            .logoutSuccessUrl("/bbs")
            .invalidateHttpSession(true));
    return http.build();
  }

  @Bean
  PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
      throws Exception {
    return authenticationConfiguration.getAuthenticationManager();
  }

}
