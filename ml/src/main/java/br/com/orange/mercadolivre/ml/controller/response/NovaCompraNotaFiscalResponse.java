package br.com.orange.mercadolivre.ml.controller.response;

import javax.validation.constraints.NotNull;

public class NovaCompraNotaFiscalResponse {

	@NotNull
	private Long idCompra;
	@NotNull
	private Long idComprador;

	public NovaCompraNotaFiscalResponse(Long idCompra, Long idComprador) {
		this.idCompra = idCompra;
		this.idComprador = idComprador;
	}

	public Long getIdCompra() {
		return idCompra;
	}

	public Long getIdComprador() {
		return idComprador;
	}

}
