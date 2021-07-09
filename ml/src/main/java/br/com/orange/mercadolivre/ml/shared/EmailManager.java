package br.com.orange.mercadolivre.ml.shared;

import br.com.orange.mercadolivre.ml.dto.PerguntaResponse;

public interface EmailManager {

	public void EnviarEmailParaVendedor(PerguntaResponse perguntaResponse);
}
