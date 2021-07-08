package br.com.orange.mercadolivre.ml.controller.request;

import java.util.Objects;
import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import org.springframework.util.Assert;

import br.com.orange.mercadolivre.ml.config.validation.Exist;
import br.com.orange.mercadolivre.ml.domain.Categoria;
import br.com.orange.mercadolivre.ml.domain.Produto;
import br.com.orange.mercadolivre.ml.domain.Usuario;
import br.com.orange.mercadolivre.ml.repository.CategoriaRepository;

public class NovoProdutoRequest {

	@NotBlank
	private String nome;
	
	@NotBlank
	@Size(max = 1000)
	private String descricao;
	
	@NotNull
	@Min(value = 1)
	private Double valor;

	@NotNull
	@Min(value = 0)
	private Integer quantidade;
	
	@NotNull
	@Exist(entity = Categoria.class, message = "Categoria não encontrada")
	private Long idCategoria;
	
	@Size(min = 3)
	@Valid
	private Set<NovaCaracteristicaProdutoRequest> caracteristicasRequest;

	public NovoProdutoRequest(@NotBlank String nome, @NotBlank @Size(max = 1000) String descricao,
			@NotBlank @NotNull @Positive @Min(1) Double valor, @NotBlank @NotNull @Positive Integer quantidade,
			@NotNull Long idCategoria, @Size(min = 3) @Valid Set<NovaCaracteristicaProdutoRequest> caracteristicasRequest) {
		this.nome = nome;
		this.descricao = descricao;
		this.valor = valor;
		this.quantidade = quantidade;
		this.idCategoria = idCategoria;
		this.caracteristicasRequest = caracteristicasRequest;
	}

	public Set<NovaCaracteristicaProdutoRequest> getCaracteristicasRequest() {
		return caracteristicasRequest;
	}

	public Produto toEntity(CategoriaRepository categoriaRepository, Usuario usuario) {
		Assert.isTrue(!Objects.isNull(idCategoria), "Id da categoria está nulo");
		Assert.isTrue(this.caracteristicasRequest.size()>=3, "Um produto deve ter 3 caracteristicas no minimo");
		return new Produto(nome, descricao, valor, quantidade, categoriaRepository.findById(idCategoria).get(), caracteristicasRequest, usuario);
	}
	
}
