package br.com.orange.mercadolivre.ml.domain;

import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.orange.mercadolivre.ml.controller.response.EventoCompraSucesso;

@Service
public class NotaFiscal implements EventoCompraSucesso {

	public void processa(Compra compra) {
		RestTemplate restTemplate = new RestTemplate();
		Map<String, Object> request = Map.of("idCompra", compra.getId(), "idComprador", compra.getUsuario().getId());
		restTemplate.postForEntity("http://localhost:8080/nota-fiscal", request, String.class);
	}

}
