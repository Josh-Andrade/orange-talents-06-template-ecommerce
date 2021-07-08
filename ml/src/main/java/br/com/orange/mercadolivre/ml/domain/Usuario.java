package br.com.orange.mercadolivre.ml.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;

@Entity
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Email
	@NotBlank
	@NotNull
	@Column(nullable = false, unique = true)
	private String login;
	
	@NotBlank
	@NotNull
	@Size(min = 6)
	@Column(nullable = false)
	private String senha;
	
	@PastOrPresent
	@Column(nullable = false)
	private LocalDateTime dataCadastro;
	
	@Deprecated
	public Usuario() {
	}
	
	public Usuario(@Email @NotBlank @NotNull String login, @NotBlank @NotNull @Size(min = 6) SenhaSemHash senha) {
		this.login = login;
		this.senha = senha.encode();
		this.dataCadastro = LocalDateTime.now();
	}

	public String getLogin() {
		return login;
	}

	public String getSenha() {
		return senha;
	}

	public Long getId() {
		return id;
	}
	
	
	
}
