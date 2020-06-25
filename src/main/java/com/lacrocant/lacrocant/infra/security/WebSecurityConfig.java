package com.lacrocant.lacrocant.infra.security;

import com.lacrocant.lacrocant.domain.admin.Role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final String LOGIN_PAGE = "/auth/login";
    
    @Autowired
	private CustomDetailService adminDetailsService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.csrf().disable()
			.authorizeRequests()
				.antMatchers("/panelAdmin").hasAuthority(Role.ADMIN.toString()) // tudo que estiver a partir dessa pasta pede autorização
				.antMatchers("/panelAdmin/*").hasAuthority(Role.ADMIN.toString())
				.antMatchers(HttpMethod.GET, "/").permitAll()
				.antMatchers(HttpMethod.GET, LOGIN_PAGE).permitAll()
				.antMatchers(HttpMethod.GET, "/*").permitAll()
				.anyRequest().authenticated()
				.and().formLogin().loginPage(LOGIN_PAGE).permitAll()
				.defaultSuccessUrl("/panelAdmin")
				.loginProcessingUrl("/auth/authentication")
				.usernameParameter("userName")
    			.passwordParameter("password")
				.failureUrl(LOGIN_PAGE+"?error=true")
				.and().logout().invalidateHttpSession(true)
				.clearAuthentication(true)
				.logoutSuccessUrl(LOGIN_PAGE).permitAll()
				.and().exceptionHandling();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(adminDetailsService).passwordEncoder(new BCryptPasswordEncoder()); //criptografando a senha
	}

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
				.antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**","/webjars/","/webjars/**"); //ignorando essas pastas para evitar erro de carregamento
    }
}