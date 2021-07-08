package br.com.orange.mercadolivre.ml.controller;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.orange.mercadolivre.ml.config.security.UsuarioAutenticado;
import br.com.orange.mercadolivre.ml.controller.request.NovoProdutoRequest;
import br.com.orange.mercadolivre.ml.domain.Usuario;
import br.com.orange.mercadolivre.ml.repository.CategoriaRepository;
import br.com.orange.mercadolivre.ml.repository.ProdutoRepository;
import br.com.orange.mercadolivre.ml.repository.UsuarioRepository;

@RestController
@RequestMapping("/produtos")
public class CadastroProdutoController {

	private ProdutoRepository produtoRepository;

	private CategoriaRepository categoriaRepository;

	private UsuarioRepository usuarioRepository;

	public CadastroProdutoController(ProdutoRepository produtoRepository, CategoriaRepository categoriaRepository,
			UsuarioRepository usuarioRepository) {
		this.produtoRepository = produtoRepository;
		this.categoriaRepository = categoriaRepository;
		this.usuarioRepository = usuarioRepository;
	}

	@PostMapping
	@Transactional
	public ResponseEntity<?> cadastrar(@RequestBody @Valid NovoProdutoRequest novoProdutoRequest,
			@AuthenticationPrincipal UsuarioAutenticado usuario) {
		produtoRepository
				.save(novoProdutoRequest.toEntity(categoriaRepository, buscarUsuario(usuario.getUsuario().getId())));
		return ResponseEntity.ok().build();
	}

	private Usuario buscarUsuario(Long id) {
		return usuarioRepository.findById(id).get();
	}
}
