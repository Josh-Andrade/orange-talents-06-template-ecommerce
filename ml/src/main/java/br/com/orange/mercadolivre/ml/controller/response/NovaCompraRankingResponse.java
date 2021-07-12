package br.com.orange.mercadolivre.ml.controller.response;

import javax.validation.constraints.NotNull;

public class NovaCompraRankingResponse {

	@NotNull
	private Long idCompra;
	@NotNull
	private Long idVendedor;

	public NovaCompraRankingResponse(Long idCompra, Long idVendedor) {
		this.idCompra = idCompra;
		this.idVendedor = idVendedor;
	}

	public Long getIdCompra() {
		return idCompra;
	}

	public Long getIdVendedor() {
		return idVendedor;
	}

}
