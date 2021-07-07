package br.com.orange.mercadolivre.ml.controller.request;

import javax.validation.constraints.NotBlank;

import br.com.orange.mercadolivre.ml.config.validation.Exist;
import br.com.orange.mercadolivre.ml.config.validation.ValorUnico;
import br.com.orange.mercadolivre.ml.domain.Categoria;
import br.com.orange.mercadolivre.ml.repository.CategoriaRepository;

public class NovaCategoriaRequest {
	
	@NotBlank
	@ValorUnico(campo = "nome", entity = "Categoria", message = "Categoria já foi cadastrada")
	private String nome;
	
	@Exist(entity = Categoria.class, message = "Categoria não foi encontrada")
	private Long idCategoria;

	public NovaCategoriaRequest(@NotBlank String nome, Long idCategoria) {
		this.nome = nome;
		this.idCategoria = idCategoria;
	}
	
	public Categoria toEntity(CategoriaRepository categoriaRepository) {
		Categoria categoria = new Categoria(nome);
		categoria.setCategoria(buscarCategoria(categoriaRepository));
		return categoria;
	}

	private Categoria buscarCategoria(CategoriaRepository categoriaRepository) {
		return idCategoria != null ? categoriaRepository.findById(idCategoria).get() : null;
	}
}
