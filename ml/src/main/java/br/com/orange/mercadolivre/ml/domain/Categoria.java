package br.com.orange.mercadolivre.ml.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

@Entity
public class Categoria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(unique = true, nullable = false)
	@NotBlank
	private String nome;
	@ManyToOne
	private Categoria categoria;

	@Deprecated
	public Categoria() {
	}

	public Long getId() {
		return this.id;
	}
	
	public Categoria(@NotBlank String nome) {
		this.nome = nome;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public String getNome() {
		return nome;
	}

	public Categoria getCategoria() {
		return categoria;
	}

}
