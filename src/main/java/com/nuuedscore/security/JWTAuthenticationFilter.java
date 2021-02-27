package com.nuuedscore.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nuuedscore.domain.Person;

/**
 * JWT Authentication  
 *
 * Servlet based authentication
 * 
 * @author PATavares
 * @since Feb 2021
 * 
 */
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private AuthenticationManager authenticationManager;

	public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;

		setFilterProcessesUrl("/person/login");
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res) throws AuthenticationException {
		try {
			Person person = new ObjectMapper().readValue(req.getInputStream(), Person.class);
			return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(person.getUsername(), person.getPassword(), new ArrayList<>()));
			
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain, Authentication auth) throws IOException {
		String token = JWT.create()
			.withSubject(((Person) auth.getPrincipal()).getUsername())
			.withExpiresAt(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
			.sign(Algorithm.HMAC512(SecurityConstants.SECRET.getBytes()));

		String body = ((Person) auth.getPrincipal()).getUsername() + " " + token;

		res.getWriter().write(body);
		res.getWriter().flush();
	}
}