package br.com.orange.mercadolivre.ml.controller.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.orange.mercadolivre.ml.config.validation.ValorUnico;
import br.com.orange.mercadolivre.ml.domain.SenhaSemHash;
import br.com.orange.mercadolivre.ml.domain.Usuario;

public class NovoUsuarioRequest {

	@Email
	@NotBlank
	@NotNull
	@ValorUnico(campo = "login", entity = "Usuario", message = "Email já cadastrado no sistema")
	private String login;

	@NotBlank
	@NotNull
	@Size(min = 6)
	private String senha;

	public NovoUsuarioRequest(@Email @NotBlank @NotNull String login,
			@NotBlank @NotNull @Size(min = 6) String senha) {
		this.login = login;
		this.senha = senha;
	}

	public Usuario toDomain() {
		return new Usuario(login, new SenhaSemHash(senha));
	}
}
