package com.example.springBootMultipleTables.securityConfig;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
		// Authenticatemanager will validate user by encrypted password
		
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		// frontend backend oke server lo unte csrf ni disable cheyyasina avasaram ledhu
		// ,
		// but here we are sending request from react application or validating API from
		// swagger so that we have to disable the csrf
		http.csrf(csrf -> csrf.disable())
				.authorizeHttpRequests(auth -> auth
						.requestMatchers("/api/auth/**", "/swagger-ui/**", "/v3/api-docs/**", "/swagger-ui.html")
						.permitAll().anyRequest().authenticated())
				;

		return http.build();
	}
//this(authenticationManager) will validate the user with encrypted password -> for that we need to create a bean for PasswordEncoder
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
			throws Exception {

		return authenticationConfiguration.getAuthenticationManager();
	}
}
