package br.com.orange.mercadolivre.ml.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.orange.mercadolivre.ml.controller.request.NovoUsuarioRequest;
import br.com.orange.mercadolivre.ml.repository.UsuarioRepository;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

	private UsuarioRepository usuarioRepository;

	public UsuarioController(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}

	@PostMapping
	public ResponseEntity<?> cadastrar(@Valid @RequestBody NovoUsuarioRequest novoUsuarioRequest) {
		usuarioRepository.save(novoUsuarioRequest.toDomain());
		return ResponseEntity.ok().build();
	}
}
