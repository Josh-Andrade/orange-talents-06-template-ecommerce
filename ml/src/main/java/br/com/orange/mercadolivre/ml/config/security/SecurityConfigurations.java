package br.com.orange.mercadolivre.ml.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations extends WebSecurityConfigurerAdapter {

	private UsuarioService usuarioService;

	private TokenManager tokenManager;

	public SecurityConfigurations(UsuarioService usuarioService, TokenManager tokenManager) {
		this.usuarioService = usuarioService;
		this.tokenManager = tokenManager;
	}

	@Override
	@Bean
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers(HttpMethod.POST, "/usuario").permitAll().antMatchers("/authorization")
				.permitAll().antMatchers(HttpMethod.POST, "/retorno-pagseguro/*").permitAll()
				.antMatchers(HttpMethod.POST, "/retorno-paypal/*").permitAll()
				.antMatchers(HttpMethod.POST, "/nota-fiscal").permitAll().antMatchers(HttpMethod.POST, "/ranking")
				.permitAll().antMatchers("/actuator/**").permitAll().anyRequest().authenticated().and()
				.csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
				.addFilterBefore(new UsuarioAutenticacaoFilter(usuarioService, tokenManager),
						UsernamePasswordAuthenticationFilter.class);
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(usuarioService).passwordEncoder(new BCryptPasswordEncoder());
	}
}
