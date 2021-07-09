package br.com.orange.mercadolivre.ml.controller.response;

public class CaracteristicasDetalheResponse {

	private String nome;
	private String descricao;

	public CaracteristicasDetalheResponse(String nome, String descricao) {
		this.nome = nome;
		this.descricao = descricao;
	}

	public String getNome() {
		return nome;
	}

	public String getDescricao() {
		return descricao;
	}

}
