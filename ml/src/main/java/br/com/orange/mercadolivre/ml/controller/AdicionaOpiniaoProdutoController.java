package br.com.orange.mercadolivre.ml.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.orange.mercadolivre.ml.config.security.UsuarioAutenticado;
import br.com.orange.mercadolivre.ml.controller.request.NovaOpiniaoProdutoRequest;
import br.com.orange.mercadolivre.ml.domain.Produto;
import br.com.orange.mercadolivre.ml.repository.ProdutoRepository;

@RestController
@RequestMapping("/produtos/opiniao")
public class AdicionaOpiniaoProdutoController {

	private ProdutoRepository produtoRepository;

	public AdicionaOpiniaoProdutoController(ProdutoRepository produtoRepository) {
		this.produtoRepository = produtoRepository;
	}

	@PostMapping("/{id}")
	public ResponseEntity<?> adicionarOpiniao(@PathVariable("id") Long idProduto,
			@RequestBody @Valid NovaOpiniaoProdutoRequest novaOpinaoProdutoRequest,
			@AuthenticationPrincipal UsuarioAutenticado usuarioAutenticado) {

		Optional<Produto> optionalProduto = produtoRepository.findById(idProduto);
		Assert.isTrue(!optionalProduto.isEmpty(), "Produto Não Encontrado");

		Produto produto = optionalProduto.get();
		produto.associarOpiniao(novaOpinaoProdutoRequest.toEntity(usuarioAutenticado.getUsuario(), produto));

		produtoRepository.save(produto);

		return ResponseEntity.ok().build();
	}
}
