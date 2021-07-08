package br.com.orange.mercadolivre.ml.config.security;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class LoginRequest {

	@NotBlank
	private String login;
	@NotBlank
	@Size(min = 6)
	private String senha;

	public LoginRequest(String login, String senha) {
		this.login = login;
		this.senha = senha;
	}

	public UsernamePasswordAuthenticationToken toAuth() {
		return new UsernamePasswordAuthenticationToken(login, senha);
	}

	
}
