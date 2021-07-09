package br.com.orange.mercadolivre.ml.controller.response;

public class CategoriaDetalheResponse {

	private String categoria;
	private CategoriaDetalheResponse categoriaMae;

	public CategoriaDetalheResponse(String categoria) {
		this.categoria = categoria;
	}

	public String getCategoria() {
		return categoria;
	}

	public CategoriaDetalheResponse getCategoriaMae() {
		return categoriaMae;
	}

	public void setCategoriaMae(CategoriaDetalheResponse categoriaMae) {
		this.categoriaMae = categoriaMae;
	}

}
