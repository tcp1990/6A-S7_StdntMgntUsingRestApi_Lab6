package com.gl.StdntMgnt.security;

import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.HttpMethod.PUT;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
	
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{
    	// configure authorization rules here
 		httpSecurity.cors().disable();
 		httpSecurity.csrf().disable();
 		httpSecurity.headers().frameOptions().disable();
 		httpSecurity.authorizeRequests().antMatchers(POST, "/api/employees/**").hasRole("ADMIN");
 		httpSecurity.authorizeRequests().antMatchers(PUT, "/api/employees/**",  "/api/roles/**", "/api/users/**").denyAll();
 		httpSecurity.authorizeRequests().antMatchers(DELETE, "/api/roles/**", "/api/users/**").denyAll();
 		//httpSecurity.authorizeRequests().antMatchers("/h2-console/**", "/login**", "/contact-us**").permitAll();
 		httpSecurity.authorizeRequests().antMatchers("/**").permitAll();
 		httpSecurity.authorizeRequests().anyRequest().authenticated().and().formLogin().and().httpBasic()
 				/*
 				 * Set the session creation policy to avoid using cookies for authentication
 				 * https://stackoverflow.com/questions/50842258/spring-security-caching-my-
 				 * authentication/50847571
 				 */
 				.and().sessionManagement().sessionCreationPolicy(STATELESS);
 		
        return httpSecurity.build();
    }
    
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder bcryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}