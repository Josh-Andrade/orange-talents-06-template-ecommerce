package br.com.orange.mercadolivre.ml.controller;

import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.orange.mercadolivre.ml.config.security.UsuarioAutenticado;
import br.com.orange.mercadolivre.ml.controller.request.AdicionaImagemRequest;
import br.com.orange.mercadolivre.ml.domain.Produto;
import br.com.orange.mercadolivre.ml.repository.ProdutoRepository;

@RestController
@RequestMapping("/produtos/imagem")
public class AdicionaImagemController {

	private ProdutoRepository produtoRepository;
	private UploaderFake uploaderFake;

	public AdicionaImagemController(ProdutoRepository produtoRepository, UploaderFake uploaderFake) {
		this.produtoRepository = produtoRepository;
		this.uploaderFake = uploaderFake;
	}

	@PostMapping("/{id}")
	@Transactional
	public ResponseEntity<?> adicionarImagem(@PathVariable("id") Long idProduto,
			@Valid AdicionaImagemRequest adicionaImagemRequest,
			@AuthenticationPrincipal UsuarioAutenticado usuarioAutenticado) {
		
		Optional<Produto> produto = buscarUsuario(idProduto, usuarioAutenticado);
		if (produto.isEmpty()) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN)
					.body("O produto informado não pertence ao usuário logado");
		}

		Set<String> links = uploaderFake.envia(adicionaImagemRequest.getImagens());
		produto.get().associarImagens(links);
		produtoRepository.save(produto.get());
		return ResponseEntity.ok().build();
	}

	private Optional<Produto> buscarUsuario(Long idProduto, UsuarioAutenticado usuarioAutenticado) {
		return produtoRepository.findByIdAndUsuario_Id(idProduto, usuarioAutenticado.getUsuario().getId());
	}
}
