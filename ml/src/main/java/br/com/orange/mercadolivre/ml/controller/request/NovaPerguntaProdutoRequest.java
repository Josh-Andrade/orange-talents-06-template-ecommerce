package br.com.orange.mercadolivre.ml.controller.request;

import javax.validation.constraints.NotBlank;

import br.com.orange.mercadolivre.ml.domain.Pergunta;
import br.com.orange.mercadolivre.ml.domain.Produto;
import br.com.orange.mercadolivre.ml.domain.Usuario;

public class NovaPerguntaProdutoRequest {

	@NotBlank
	private String titulo;

	public NovaPerguntaProdutoRequest() {
		// TODO Auto-generated constructor stub
	}

	public NovaPerguntaProdutoRequest(@NotBlank String titulo) {
		this.titulo = titulo;
	}

	public Pergunta toEntity(Produto produto, Usuario usuario) {
		return new Pergunta(titulo, produto, usuario);
	}

	public String getTitulo() {
		return titulo;
	}

	
}
