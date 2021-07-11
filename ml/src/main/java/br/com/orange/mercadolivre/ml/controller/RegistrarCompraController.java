package br.com.orange.mercadolivre.ml.controller;

import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.util.Assert;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.orange.mercadolivre.ml.config.security.UsuarioAutenticado;
import br.com.orange.mercadolivre.ml.config.validation.EstoqueValidator;
import br.com.orange.mercadolivre.ml.controller.request.NovaCompraRequest;
import br.com.orange.mercadolivre.ml.controller.response.CompraResponse;
import br.com.orange.mercadolivre.ml.domain.Compra;
import br.com.orange.mercadolivre.ml.domain.Produto;
import br.com.orange.mercadolivre.ml.repository.CompraRepository;
import br.com.orange.mercadolivre.ml.repository.ProdutoRepository;
import br.com.orange.mercadolivre.ml.shared.EmailManager;

@RestController
@RequestMapping("/produtos/comprar")
public class RegistrarCompraController {

	private ProdutoRepository produtoRepository;

	private CompraRepository compraRepository;

	private EmailManager emailManager;

	private EstoqueValidator estoqueValidator;

	public RegistrarCompraController(ProdutoRepository produtoRepository, CompraRepository compraRepository,
			EmailManager emailManager, EstoqueValidator estoqueValidator) {
		this.produtoRepository = produtoRepository;
		this.compraRepository = compraRepository;
		this.emailManager = emailManager;
		this.estoqueValidator = estoqueValidator;
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(estoqueValidator);
	}

	@PostMapping
	@Transactional
	public ResponseEntity<String> registrarCompra(@RequestBody @Valid NovaCompraRequest novaCompraRequest,
			@AuthenticationPrincipal UsuarioAutenticado usuarioAutenticado, UriComponentsBuilder uriComponentsBuilder) {

		Produto produto = validarProduto(novaCompraRequest).get();
		produto.abaterEstoque(novaCompraRequest.getQuantidade());

		Compra compra = novaCompraRequest.toEntity(produto, usuarioAutenticado.getUsuario());
		compraRepository.save(compra);

		enviaEmailVendedor(compra.toResponse());
		return ResponseEntity.status(HttpStatus.FOUND).body(compra.getGatewayPagamento()
				.retornaUrlGateway(compra.getId(), compra.getGatewayPagamento(), uriComponentsBuilder));
	}

	private void enviaEmailVendedor(CompraResponse compraResponse) {
		emailManager.enviarEmailVendaParaVendedor(compraResponse);
	}

	private Optional<Produto> validarProduto(NovaCompraRequest novaCompraRequest) {
		Optional<Produto> optionalProduto = produtoRepository.findById(novaCompraRequest.getIdProduto());
		Assert.isTrue(optionalProduto.isPresent(), "Produto não encontrado");
		return optionalProduto;
	}
}
