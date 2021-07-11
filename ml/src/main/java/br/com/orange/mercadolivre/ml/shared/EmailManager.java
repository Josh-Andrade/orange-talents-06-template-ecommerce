package br.com.orange.mercadolivre.ml.shared;

import br.com.orange.mercadolivre.ml.controller.response.CompraResponse;
import br.com.orange.mercadolivre.ml.dto.PerguntaResponse;

public interface EmailManager {

	public void enviarEmailPerguntaParaVendedor(PerguntaResponse perguntaResponse);
	
	public void enviarEmailVendaParaVendedor(CompraResponse compraResponse);
}
