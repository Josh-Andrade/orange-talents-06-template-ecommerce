package br.com.orange.mercadolivre.ml.domain;

import org.springframework.web.util.UriComponentsBuilder;

public enum GatewayPagamento {

	PAYPAL("paypal.com", "/retorno-paypal/{id}"), PAGSEGURO("pagseguro.com", "/retorno-pagseguro/{id}");

	private String url;
	private String urlRetorno;

	GatewayPagamento(String url, String urlRetorno) {
		this.url = url;
		this.urlRetorno = urlRetorno;
	}

	public String getUrl() {
		return url;
	}

	public String retornaUrlGateway(Long idCompra, GatewayPagamento gatewayPagamento, UriComponentsBuilder uriComponentsBuilder) {
		return gatewayPagamento.getUrl() + "?buyerId=" + idCompra + "&redirectUrl=" + uriComponentsBuilder.path(urlRetorno).buildAndExpand(idCompra).toString();
	}
}
