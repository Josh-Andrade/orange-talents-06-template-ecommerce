package br.com.orange.mercadolivre.ml.controller.response;

import br.com.orange.mercadolivre.ml.domain.Compra;

public interface EventoCompraSucesso {
	
	public void processa(Compra compra);
}
