package com.todo.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.todo.demo.entity.User;
import com.todo.demo.service.UserService;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	
	private UserService userService;
	
	
	@Override
	public void configure(WebSecurity web) throws Exception{
		
		web.ignoring().antMatchers("/h2Admin/**");
	}
	
	@Override
	public void configure(HttpSecurity http) throws Exception{
		
		http.formLogin().defaultSuccessUrl("/todo/todolist").and().logout().permitAll().and().authorizeRequests().antMatchers("/**").hasRole("USER");
	}

	
	@Override
	public void configure(AuthenticationManagerBuilder authBuilder) throws Exception {

		authBuilder.authenticationProvider(authenticationProvider() );
	}

	
	@Bean
	public AuthenticationProvider authenticationProvider() {
		
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		
		authenticationProvider.setPasswordEncoder( passEncoder());
		authenticationProvider.setUserDetailsService(userService);
		
		return  authenticationProvider;
	}
	
	@Bean 
	public PasswordEncoder passEncoder() {
		
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public ApplicationRunner initializeUser(PasswordEncoder passEncoder) {
		
		final User defaultUser = new User("admin",passEncoder.encode("password"));
 

		return args -> userService.save(defaultUser); 
	}

}
