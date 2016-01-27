package com.jason;

import javax.inject.Inject;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
/**
 * @EnableGlobalMethodSecurity as part of the requirement that only admin users can delete a poll. This
 *                             annotation activates method level security (see pollController.deletePoll)
 * @author Jason
 *
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Inject
	private UserDetailsService userDetailsService;

	/**
	 * BCryptPasswordEncoder because thats the method we've chosen to encrypt in import sql
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	}

	/**
	 * Default Security Config protects all endpoints. to just secure v3 Poll API, we override another
	 * WebSecurityConfigurer's config
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// v1 and v2 will be made anonymously via antMatchers and permitAll(). antMatchers and
		// authenticated() to define which endpoints are protected
		// csrf is disabled
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
				.authorizeRequests().antMatchers("/v1/**", "/v2/**", "/swagger-ui/**", "/api-docs/**")
				.permitAll().antMatchers("/v3/polls/ **").authenticated().and().httpBasic()
				.realmName("Quick Poll").and().csrf().disable();
	}
	
	/**
	 * for oauth
	 */
    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
            return super.authenticationManager();
    }
}
