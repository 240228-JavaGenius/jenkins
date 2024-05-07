package com.blit.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.blit.service.UserServiceImpl;

//Configure my Security Filter Bean
@Configuration
@EnableWebSecurity //ensures web security context.
public class SecurityConfig {
	//SecurityChain allows for chaining multiple methods 
	//together for configuration. HttpSecurity object
	//represents the preconfigured security 
	//configuration/context
//	@Autowired
//	private UserServiceImpl userService;
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		return http
				.csrf((c)->{c.disable();})
				.cors((c)->Customizer.withDefaults())
				.authorizeHttpRequests(//Authenticate all requests
						(c)->{c.requestMatchers(HttpMethod.GET,
								"api/v1/car/*").hasAnyRole("ADMIN","USER")
						.requestMatchers(HttpMethod.POST, 
								"api/v1/car/*").hasRole("ADMIN")
						.requestMatchers(HttpMethod.DELETE, 
								"api/v1/car/*").hasRole("ADMIN")
						.requestMatchers(HttpMethod.PUT, 
								"api/v1/car/*").hasRole("ADMIN")
						.anyRequest().authenticated();
						})
				.httpBasic(Customizer.withDefaults())//Basic configuration with web defaults.
				.build();
	}
	
	/*
	 * @Bean public UserDetailsService userDetailsService() { UserDetails admin =
	 * User.builder() .username("admin") .password(bCryptPasswordEncoder()
	 * .encode("password")) .roles("ADMIN") .build(); UserDetails user =
	 * User.builder() .username("user") .password(bCryptPasswordEncoder().encode
	 * ("password")) .roles("USER") .build(); return new
	 * InMemoryUserDetailsManager(admin, user); }
	 */
	
	@Autowired
	private UserServiceImpl userService;
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth)
		throws Exception{
		auth.userDetailsService(userService)
		.passwordEncoder(new BCryptPasswordEncoder());
	}
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
}
