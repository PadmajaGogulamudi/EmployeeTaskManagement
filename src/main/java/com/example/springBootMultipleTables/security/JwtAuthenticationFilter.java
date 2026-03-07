package com.example.springBootMultipleTables.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{
	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	@Autowired
	private CustomUserDetailsService customUserDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		
		//get token from headers
		String token=getToken(request);
		
		//check token valid or not ,if valid we will load the user and set the authentication 
		if(StringUtils.hasText(token) && jwtTokenProvider.validateToken(token)) {
			String email=jwtTokenProvider.getEmailFromToken(token);
			//after token check if it pass then load the user 
			UserDetails userdetails=customUserDetailsService.loadUserByUsername(email);
			//after loading authenticate user with username and password
			UsernamePasswordAuthenticationToken authentication=new UsernamePasswordAuthenticationToken(userdetails, null,userdetails.getAuthorities());
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}
		filterChain.doFilter(request,response);
		
		
		
	}
	private String getToken(HttpServletRequest request) {
		String token=request.getHeader("Authorization");
		if(StringUtils.hasText(token) && token.startsWith("Bearer")) {
			return token.substring(7,token.length());
		}
		return null;
	}
	

}
