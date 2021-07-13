package br.com.orange.mercadolivre.ml.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.util.Assert;

import br.com.orange.mercadolivre.ml.controller.request.NovaCaracteristicaProdutoRequest;
import br.com.orange.mercadolivre.ml.controller.response.CaracteristicasDetalheResponse;
import br.com.orange.mercadolivre.ml.controller.response.CategoriaDetalheResponse;
import br.com.orange.mercadolivre.ml.controller.response.ImagensDetalheResponse;
import br.com.orange.mercadolivre.ml.controller.response.OpinioesDetalheResponse;
import br.com.orange.mercadolivre.ml.controller.response.PerguntasDetalheResponse;

@Entity
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Column(nullable = false)
	private String nome;

	@NotBlank
	@Size(max = 1000)
	@Column(length = 1000, nullable = false)
	private String descricao;

	@NotNull
	@Min(value = 1)
	@Column(nullable = false)
	private Double valor;

	@NotNull
	@Min(value = 0)
	@Column(nullable = false)
	private Integer quantidade;

	@NotNull
	@ManyToOne
	private Categoria categoria;

	@NotNull
	@Column(nullable = false)
	private LocalDateTime dataCadastro;

	@Size(min = 3)
	@OneToMany(mappedBy = "produto", cascade = CascadeType.PERSIST)
	private Set<CaracteristicaProduto> caracteristicas;

	@NotNull
	@ManyToOne
	private Usuario usuario;

	@OneToMany(mappedBy = "produto", cascade = CascadeType.MERGE)
	private Set<ImagemProduto> imagens = new HashSet<>();

	@OneToMany(mappedBy = "produto", cascade = CascadeType.MERGE)
	private List<OpiniaoProduto> opinioes = new ArrayList<>();

	@OneToMany(mappedBy = "produto", cascade = CascadeType.MERGE)
	private List<Pergunta> perguntas = new ArrayList<>();

	@Deprecated
	public Produto() {
	}

	public Produto(@NotBlank String nome, @NotBlank String descricao, @Min(1) Double valor,
			@Min(value = 0) Integer quantidade, Categoria categoria,
			@Size(min = 3) Set<NovaCaracteristicaProdutoRequest> caracteristicas, @NotNull Usuario usuario) {
		this.nome = nome;
		this.descricao = descricao;
		this.valor = valor;
		this.quantidade = quantidade;
		this.categoria = categoria;
		this.dataCadastro = LocalDateTime.now();
		this.caracteristicas = caracteristicas.stream().map(c -> c.toEntity(this)).collect(Collectors.toSet());
		this.usuario = usuario;
		Assert.isTrue(this.caracteristicas.size() >= 3,
				"Ocorreu um erro no seguinte campo: caracteristicas, Cada produto deve ter no minimo 3 caracteristicas distintas, verifique se não enviou alguma repetida");
	}

	public Set<CaracteristicaProduto> getCaracteristicas() {
		return caracteristicas;
	}

	public void associarImagens(Set<String> links) {
		Set<ImagemProduto> imagens = links.stream().map(link -> new ImagemProduto(this, link))
				.collect(Collectors.toSet());
		this.imagens.addAll(imagens);
	}

	public String getNome() {
		return nome;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public String getDescricao() {
		return descricao;
	}

	public Double getValor() {
		return valor;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public Double getMediaNotas() {
		return opinioes.stream().mapToDouble(o -> o.getNota()).average().getAsDouble();
	}

	public Integer getTotalNotas() {
		return opinioes.size();
	}

	public List<CaracteristicasDetalheResponse> getDetalheCaracteristicas() {
		return caracteristicas.stream().map(c -> new CaracteristicasDetalheResponse(c.getNome(), c.getDescricao()))
				.collect(Collectors.toList());
	}

	public List<OpinioesDetalheResponse> getDetalheOpinioes() {
		return opinioes.stream().map(o -> new OpinioesDetalheResponse(o.getNota(), o.getTitulo(), o.getDescricao()))
				.collect(Collectors.toList());
	}

	public List<PerguntasDetalheResponse> getDetalhePerguntas() {
		return perguntas.stream().map(p -> new PerguntasDetalheResponse(p.getTitulo())).collect(Collectors.toList());
	}

	public List<ImagensDetalheResponse> getDetalheImagens() {
		return imagens.stream().map(i -> new ImagensDetalheResponse(i.getLink())).collect(Collectors.toList());
	}

	public CategoriaDetalheResponse getDetalheCategorias() {
		return carregarCategoriasMae(categoria);
	}

	private CategoriaDetalheResponse carregarCategoriasMae(Categoria categoria) {
		if (Objects.isNull(categoria)) {
			return null;
		}
		CategoriaDetalheResponse categoriaDetalhe = new CategoriaDetalheResponse(categoria.getNome());
		categoriaDetalhe.setCategoriaMae(carregarCategoriasMae(categoria.getCategoria()));
		return categoriaDetalhe;
	}

	public void abaterEstoque(Integer quantidade) {
		Assert.isTrue(this.quantidade >= quantidade, "O produto informado não possui em estoque a quantidade solicitada");
		this.quantidade -= quantidade;
	}
}
