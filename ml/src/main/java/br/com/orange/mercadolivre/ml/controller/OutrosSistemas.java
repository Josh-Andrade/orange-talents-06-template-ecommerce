package br.com.orange.mercadolivre.ml.controller;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.orange.mercadolivre.ml.controller.response.NovaCompraNotaFiscalResponse;
import br.com.orange.mercadolivre.ml.controller.response.NovaCompraRankingResponse;

@RestController
public class OutrosSistemas {

	@PostMapping(value = "/nota-fiscal")
	public void gerarNota(@RequestBody @Valid NovaCompraNotaFiscalResponse request) throws InterruptedException {
		System.out.println(
				"Gerando nota fiscal para " + request.getIdCompra() + " do comprador " + request.getIdComprador());
		Thread.sleep(150);
	}

	@PostMapping(value = "/ranking")
	public void ranking(@RequestBody @Valid NovaCompraRankingResponse request) throws InterruptedException {
		System.out.println("Gerando Ranking para " + request.getIdCompra() + " do vendedor " + request.getIdVendedor());
		Thread.sleep(150);
	}
}
