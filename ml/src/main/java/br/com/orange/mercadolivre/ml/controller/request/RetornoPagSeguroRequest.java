package br.com.orange.mercadolivre.ml.controller.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.orange.mercadolivre.ml.domain.Compra;
import br.com.orange.mercadolivre.ml.domain.Transacao;

public class RetornoPagSeguroRequest implements RetornoGatewayRequest{

	@NotBlank
	private String idTransacao;
	@NotNull
	private StatusRetornoPagseguro statusRetornoGateway;

	public RetornoPagSeguroRequest(String idTransacao, StatusRetornoPagseguro statusRetornoGateway) {
		this.idTransacao = idTransacao;
		this.statusRetornoGateway = statusRetornoGateway;
	}

	public Transacao toEntity(Compra compra) {
		return new Transacao(idTransacao, compra, statusRetornoGateway.definirStatusRetorno());
	}

	@Override
	public String getIdTransacao() {
		return idTransacao;
	}
}
