package com.projeto.academia.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.projeto.academia.filter.FiltroToken;
import com.projeto.academia.service.UsuarioDetailsService;

@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(
		  prePostEnabled = true, 
		  securedEnabled = true, 
		  jsr250Enabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private final UsuarioDetailsService usuarioDetailsService;

	public SecurityConfig(UsuarioDetailsService usuarioDetailsService) {
		this.usuarioDetailsService = usuarioDetailsService;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		http.csrf().disable()
	    .authorizeRequests()
	    	
	        .antMatchers("/").permitAll()
	        .antMatchers("/login").permitAll()
	        .antMatchers("/usuario/**").hasAnyRole("ADMINISTRADOR", "SECRETARIO")
	        .antMatchers("/aluno/**").hasAnyRole("ADMINISTRADOR", "SECRETARIO")
	        
	        .anyRequest().authenticated()
	        .and()
	        .addFilterAfter(new FiltroToken(),UsernamePasswordAuthenticationFilter.class)
	    .logout()
	        .logoutUrl("/logout")
	        .logoutSuccessUrl("/login")
	        .invalidateHttpSession(true)
	        .deleteCookies("JSESSIONID")
	        .and()
//	    .formLogin()
//	        .permitAll()
//	        .and()
	    .httpBasic();

	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub

//		auth.inMemoryAuthentication()
//		.passwordEncoder(passwordEncoder())
//		.withUser("paulo").password("123").roles("ADMIN");

		auth.userDetailsService(usuarioDetailsService).passwordEncoder(passwordEncoder());
	}
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
