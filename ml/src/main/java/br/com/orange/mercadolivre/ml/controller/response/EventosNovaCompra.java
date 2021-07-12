package br.com.orange.mercadolivre.ml.controller.response;

import java.util.Set;

import br.com.orange.mercadolivre.ml.domain.Compra;

public class EventosNovaCompra {

	private Set<EventoCompraSucesso> eventos;

	public boolean processa(Compra compra) {
		if (compra.processadaComSucesso()) {
			eventos.forEach(evento -> evento.processa(compra));
			// emailManager.enviarEmailCompraBemSucedidaParaCliente(compra);
			return true;
		}
		return false;
	}

}
