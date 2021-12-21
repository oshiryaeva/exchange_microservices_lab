package com.exchange.traderapi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import com.exchange.traderapi.service.CustomUserDetailService;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private CustomUserDetailService customUserDetailService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	    http
	            .csrf().disable()
	            .authorizeRequests() 
	            .antMatchers("/**").hasRole("USER")
	            .anyRequest().authenticated()
	            .and()
	            .httpBasic()
	            .and()
	            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}

	/*
	@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("trader")
                .password("{noop}12345")
                .roles(Role.USER)
                .and()
                .withUser("application")
                .password("{noop}12345")
                .roles(Role.APPLICATION)
                .and()
                .withUser("Mike")
                .password("{noop}12345")
                .roles(Role.USER);
    }
	*/
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(customUserDetailService);
	}
	
	/*
	@Bean
	@Override
	protected UserDetailsService userDetailsService() {
		 
		//Trader
        UserDetails theUser = User.withUsername("trader")
                        .passwordEncoder(PasswordEncoderFactories.createDelegatingPasswordEncoder()::encode)
                        .password("12345").roles("ROLE_USER").build();
        
        //Application Role 
        UserDetails theApplication = User.withUsername("application")
                .passwordEncoder(PasswordEncoderFactories.createDelegatingPasswordEncoder()::encode)
                .password("12345").roles("ROLE_APPLICATION").build();
  
        InMemoryUserDetailsManager userDetailsManager = new InMemoryUserDetailsManager();
              
        userDetailsManager.createUser(theUser);
        userDetailsManager.createUser(theApplication);
        
        return userDetailsManager;
	}
	*/
	
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
	
}
