package br.com.orange.mercadolivre.ml.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.orange.mercadolivre.ml.controller.request.NovaCategoriaRequest;
import br.com.orange.mercadolivre.ml.repository.CategoriaRepository;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {

	private CategoriaRepository categoriaRepository;

	public CategoriaController(CategoriaRepository categoriaRepository) {
		this.categoriaRepository = categoriaRepository;
	}

	@PostMapping
	public ResponseEntity<?> cadastrar(@RequestBody @Valid NovaCategoriaRequest novaCategoriaRequest) {
		categoriaRepository.save(novaCategoriaRequest.toEntity(categoriaRepository));
		return ResponseEntity.ok().build();
	}
}
