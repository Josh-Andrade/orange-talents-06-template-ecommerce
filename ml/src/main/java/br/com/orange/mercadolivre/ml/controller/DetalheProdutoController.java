package br.com.orange.mercadolivre.ml.controller;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.orange.mercadolivre.ml.controller.response.ProdutoDetalheResponse;
import br.com.orange.mercadolivre.ml.domain.Produto;
import br.com.orange.mercadolivre.ml.repository.ProdutoRepository;

@RestController
@RequestMapping("/produtos/detalhes")
public class DetalheProdutoController {

	private ProdutoRepository produtoRepository;

	public DetalheProdutoController(ProdutoRepository produtoRepository) {
		this.produtoRepository = produtoRepository;
	}

	@GetMapping("/{id}")
	public ResponseEntity<ProdutoDetalheResponse> detalharProduto(@PathVariable("id") Long idProduto) {
		Optional<Produto> optionalProduto = produtoRepository.findById(idProduto);
		Assert.isTrue(optionalProduto.isPresent(), "O produto informado não foi encontrado");
		return ResponseEntity.ok(new ProdutoDetalheResponse(optionalProduto.get()));
	}
}
