package br.com.orange.mercadolivre.ml.domain;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

import br.com.orange.mercadolivre.ml.dto.PerguntaResponse;

@Entity
public class Pergunta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	private String titulo;

	@PastOrPresent
	private LocalDateTime dataCadastro;

	@NotNull
	@Valid
	@ManyToOne
	private Produto produto;

	@NotNull
	@Valid
	@ManyToOne
	private Usuario usuario;

	@Deprecated
	public Pergunta() {
	}
	
	public Pergunta(@NotBlank String titulo, @NotNull @Valid Produto produto, @NotNull @Valid Usuario usuario) {
		this.titulo = titulo;
		this.produto = produto;
		this.usuario = usuario;
		this.dataCadastro = LocalDateTime.now();
	}

	public PerguntaResponse toResponse(String email) {
		return new PerguntaResponse(titulo, email, produto.getNome());
	}

	public String getTitulo() {
		return titulo;
	}

}
