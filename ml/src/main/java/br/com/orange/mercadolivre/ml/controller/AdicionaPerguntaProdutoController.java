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
import br.com.orange.mercadolivre.ml.controller.request.NovaPerguntaProdutoRequest;
import br.com.orange.mercadolivre.ml.domain.Pergunta;
import br.com.orange.mercadolivre.ml.domain.Produto;
import br.com.orange.mercadolivre.ml.repository.PerguntaRepository;
import br.com.orange.mercadolivre.ml.repository.ProdutoRepository;
import br.com.orange.mercadolivre.ml.repository.UsuarioRepository;
import br.com.orange.mercadolivre.ml.shared.EmailManager;

@RestController
@RequestMapping("/produtos/pergunta")
public class AdicionaPerguntaProdutoController {

	private ProdutoRepository produtoRepository;

	private PerguntaRepository perguntaRepository;

	private EmailManager emailManager;

	private UsuarioRepository usuarioRepository;

	public AdicionaPerguntaProdutoController(ProdutoRepository produtoRepository, PerguntaRepository perguntaRepository,
			EmailManager emailManager, UsuarioRepository usuarioRepository) {
		this.produtoRepository = produtoRepository;
		this.perguntaRepository = perguntaRepository;
		this.emailManager = emailManager;
		this.usuarioRepository = usuarioRepository;
	}

	@PostMapping("/{id}")
	public ResponseEntity<?> adicionarPergunta(@PathVariable("id") Long idProduto,
			@RequestBody @Valid NovaPerguntaProdutoRequest novaPerguntaProdutoRequest,
			@AuthenticationPrincipal UsuarioAutenticado usuarioAutenticado) {

		Optional<Produto> optionalProduto = produtoRepository.findById(idProduto);
		Assert.isTrue(optionalProduto.isPresent(), "Produto Não Encontrado");

		Pergunta pergunta = novaPerguntaProdutoRequest.toEntity(optionalProduto.get(), usuarioAutenticado.getUsuario());

		perguntaRepository.save(pergunta);

		emailManager.EnviarEmailParaVendedor(pergunta.toResponse(usuarioRepository.findEmailById(optionalProduto.get().getUsuario().getId())));

		return ResponseEntity.ok().build();
	}

}
