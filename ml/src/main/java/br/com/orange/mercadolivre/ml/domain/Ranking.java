package br.com.orange.mercadolivre.ml.domain;

import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.orange.mercadolivre.ml.controller.response.EventoCompraSucesso;

@Service
public class Ranking implements EventoCompraSucesso {

	public void processa(Compra compra) {
		RestTemplate restTemplate = new RestTemplate();
		Map<String, Object> request = Map.of("idCompra", compra.getId(), "idVendedor",
				compra.getProduto().getUsuario().getId());
		restTemplate.postForEntity("http://localhost:8080/ranking", request, String.class);
	}

}
