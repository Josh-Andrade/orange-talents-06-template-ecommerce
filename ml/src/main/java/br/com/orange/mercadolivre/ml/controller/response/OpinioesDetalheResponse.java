package br.com.orange.mercadolivre.ml.controller.response;

public class OpinioesDetalheResponse {

	private Integer nota;
	private String titulo;
	private String descricao;

	public OpinioesDetalheResponse(Integer nota, String titulo, String descricao) {
		this.nota = nota;
		this.titulo = titulo;
		this.descricao = descricao;
	}

	public Integer getNota() {
		return nota;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getDescricao() {
		return descricao;
	}

}
