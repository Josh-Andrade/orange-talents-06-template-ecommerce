package br.com.orange.mercadolivre.ml.controller.request;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.orange.mercadolivre.ml.domain.OpiniaoProduto;
import br.com.orange.mercadolivre.ml.domain.Produto;
import br.com.orange.mercadolivre.ml.domain.Usuario;

public class NovaOpiniaoProdutoRequest {

	@NotNull
	@Min(value = 1)
	@Max(value = 5)
	private Integer nota;
	
	@NotBlank
	private String titulo;
	
	@NotBlank
	@Size(max = 500)
	private String descricao;
	
	public NovaOpiniaoProdutoRequest(@NotNull @Size(max = 5, min = 1) Integer nota, @NotBlank String titulo,
			@NotBlank @Size(max = 500) String descricao) {
		this.nota = nota;
		this.titulo = titulo;
		this.descricao = descricao;
	}

	public OpiniaoProduto toEntity(Usuario usuario, Produto produto) {
		return new OpiniaoProduto(nota, titulo, descricao, produto, usuario);
	}
}
