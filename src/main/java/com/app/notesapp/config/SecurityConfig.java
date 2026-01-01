package com.app.notesapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
//		return http
//			.securityMatcher("/**")
//			.csrf(csrf-> csrf.disable())
//			.formLogin(form -> form.disable()) 
//			.httpBasic(basic -> basic.disable())
//			.authorizeHttpRequests(auth-> auth
//					.requestMatchers("/register","/users","/login","/").permitAll()
//					.anyRequest().authenticated()
//			)
//			.build();
		http
				.sessionManagement(session ->
	            session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	        )
	        .csrf(csrf -> csrf.disable())
	        .formLogin(form -> form.disable())
	        .httpBasic(basic -> basic.disable())
	        .logout(logout -> logout.disable())
	        .authorizeHttpRequests(auth -> auth
	            .requestMatchers("/register", "/login","/error").permitAll()
	            .anyRequest().authenticated()
	        );
		
		return http.build();
	}
	
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public AuthenticationProvider authProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService);;
		provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
		return provider;
	}
}
