package com.nuuedscore.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

import com.nuuedscore.security.JWTAuthenticationFilter;
import com.nuuedscore.security.JWTAuthorizationFilter;
import com.nuuedscore.security.SecurityConstants;
import com.nuuedscore.service.impl.PersonService;

/**
 * Security Configuration 
 * 
 * CORS
 * Since we need to Enable Cross Origin Resource Sharing for client connections to the microservice...
 * https://developer.mozilla.org/es/docs/Web/HTTP/Access_control_CORS
 * We need to activate CORS in Spring Security also.
 * 
 * JWT
 * Includes JWT Authentication and Authorization
 * 
 * @author PATavares
 * @since Feb 2021
 * 
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    PersonService personService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(personService);
    }
    
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.cors()
			.and()
			.csrf().disable()
			.authorizeRequests()
            .antMatchers(HttpMethod.POST, SecurityConstants.SIGN_UP_URL).permitAll()
            .anyRequest().authenticated()
            .and()
            .addFilter(new JWTAuthenticationFilter(authenticationManager()))
            .addFilter(new JWTAuthorizationFilter(authenticationManager()))
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}
}