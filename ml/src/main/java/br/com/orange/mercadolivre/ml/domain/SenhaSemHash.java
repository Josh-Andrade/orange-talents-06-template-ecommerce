package br.com.orange.mercadolivre.ml.domain;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class SenhaSemHash {

	private String senha;

	public SenhaSemHash(@NotBlank @Size(min = 6) String senha) {
		this.senha = senha;
	}
	
	public String encode() {
		return new BCryptPasswordEncoder().encode(senha);
	}
	
}
