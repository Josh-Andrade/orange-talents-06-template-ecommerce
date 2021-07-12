package br.com.orange.mercadolivre.ml.controller.request;

import br.com.orange.mercadolivre.ml.domain.Compra;
import br.com.orange.mercadolivre.ml.domain.Transacao;

public interface RetornoGatewayRequest {

	public Transacao toEntity(Compra compra);
	
	public String getIdTransacao();
}
