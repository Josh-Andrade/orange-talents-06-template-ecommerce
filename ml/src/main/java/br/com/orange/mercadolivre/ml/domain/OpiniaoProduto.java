package br.com.orange.mercadolivre.ml.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class OpiniaoProduto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Min(value = 1)
	@Max(value = 5)
	private Integer nota;
	
	@NotBlank
	private String titulo;

	@NotBlank
	@Size(max = 500)
	@Column(length = 500)
	private String descricao;

	@ManyToOne
	@NotNull
	@Valid
	private Produto produto;

	@ManyToOne
	@NotNull
	@Valid
	private Usuario usuario;

	@Deprecated
	public OpiniaoProduto() {
	}

	public OpiniaoProduto(@NotNull @Min(1) @Max(5) Integer nota, @NotBlank String titulo,
			@NotBlank @Size(max = 500) String descricao, @NotNull @Valid Produto produto,
			@NotNull @Valid Usuario usuario) {
		this.nota = nota;
		this.titulo = titulo;
		this.descricao = descricao;
		this.produto = produto;
		this.usuario = usuario;
	}



}
