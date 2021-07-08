package br.com.orange.mercadolivre.ml.controller.request;

import javax.validation.constraints.NotBlank;

import br.com.orange.mercadolivre.ml.domain.CaracteristicaProduto;
import br.com.orange.mercadolivre.ml.domain.Produto;

public class NovaCaracteristicaProdutoRequest {
	
	@NotBlank
	private String nome;
	
	@NotBlank
	private String descricao;

	public NovaCaracteristicaProdutoRequest(@NotBlank String nome, @NotBlank String descricao) {
		this.nome = nome;
		this.descricao = descricao;
	}

	public CaracteristicaProduto toEntity(Produto produto) {
		return new CaracteristicaProduto(nome, descricao, produto);
	}
	
	
}
