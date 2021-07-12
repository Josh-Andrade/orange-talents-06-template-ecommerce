package br.com.orange.mercadolivre.ml.controller;

import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.orange.mercadolivre.ml.controller.request.RetornoGatewayRequest;
import br.com.orange.mercadolivre.ml.controller.request.RetornoPagSeguroRequest;
import br.com.orange.mercadolivre.ml.controller.request.RetornoPaypalRequest;
import br.com.orange.mercadolivre.ml.controller.response.EventoCompraSucesso;
import br.com.orange.mercadolivre.ml.domain.Compra;
import br.com.orange.mercadolivre.ml.repository.CompraRepository;
import br.com.orange.mercadolivre.ml.shared.EmailManager;

@RestController
@RequestMapping
public class FinalizaCompraController {

	private CompraRepository compraRepository;

	private EmailManager emailManager;

	private Set<EventoCompraSucesso> eventos;

	public FinalizaCompraController(CompraRepository compraRepository, EmailManager emailManager,
			Set<EventoCompraSucesso> eventoCompraSucesso) {
		super();
		this.compraRepository = compraRepository;
		this.emailManager = emailManager;
		this.eventos = eventoCompraSucesso;
	}

	@PostMapping("/retorno-pagseguro/{id}")
	@Transactional
	public ResponseEntity<?> processaPagamentoPagseguro(@PathVariable("id") Long idCompra,
			@RequestBody @Valid RetornoPagSeguroRequest retornoPagSeguroRequest) {
		return processa(idCompra, retornoPagSeguroRequest);
	}

	@PostMapping("/retorno-paypal/{id}")
	@Transactional
	public ResponseEntity<?> processaPagamentoPaypal(@PathVariable("id") Long idCompra,
			@RequestBody @Valid RetornoPaypalRequest retornoPayPalRequest) {
		return processa(idCompra, retornoPayPalRequest);
	}

	private ResponseEntity<?> processa(Long idCompra, RetornoGatewayRequest retornoGatewayRequest) {
		Optional<Compra> optionalCompra = compraRepository.findById(idCompra);
		Assert.isTrue(optionalCompra.isPresent(), "Compra não encontrada");
		Compra compra = optionalCompra.get();

		compra.atribuirTransacao(retornoGatewayRequest);
		compraRepository.save(compra);

		if (processadaComSucesso(compra)) {
			return ResponseEntity.ok("SUCESSO");
		}

		emailManager.enviarEmailCompraMalSucedidaParaCliente(compra);
		return ResponseEntity.ok("ERRO");
	}

	private Boolean processadaComSucesso(Compra compra) {
		if (compra.processadaComSucesso()) {
			eventos.forEach(evento -> evento.processa(compra));
			emailManager.enviarEmailCompraBemSucedidaParaCliente(compra);
			return true;
		}
		return false;

	}
}
