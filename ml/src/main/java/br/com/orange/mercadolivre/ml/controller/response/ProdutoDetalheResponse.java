package br.com.orange.mercadolivre.ml.controller.response;

import java.util.List;

import br.com.orange.mercadolivre.ml.domain.Produto;

public class ProdutoDetalheResponse {

	private String nome;
	private Double valor;
	private String descricao;
	private Double mediaNotas;
	private Integer totalNotas;
	private List<CaracteristicasDetalheResponse> caracteristicas;
	private List<OpinioesDetalheResponse> opinioes;
	private List<PerguntasDetalheResponse> perguntas;
	private List<ImagensDetalheResponse> imagens;
	private CategoriaDetalheResponse categoria;

	public ProdutoDetalheResponse(Produto produto) {
		this.nome = produto.getNome();
		this.valor = produto.getValor();
		this.descricao = produto.getDescricao();
		this.mediaNotas = produto.getMediaNotas();
		this.totalNotas = produto.getTotalNotas();
		this.caracteristicas = produto.getDetalheCaracteristicas();
		this.opinioes = produto.getDetalheOpinioes();
		this.perguntas = produto.getDetalhePerguntas();
		this.imagens = produto.getDetalheImagens();
		this.categoria = produto.getDetalheCategorias();
	}

	public String getNome() {
		return nome;
	}

	public Double getValor() {
		return valor;
	}

	public String getDescricao() {
		return descricao;
	}

	public Double getMediaNotas() {
		return mediaNotas;
	}

	public Integer getTotalNotas() {
		return totalNotas;
	}

	public List<CaracteristicasDetalheResponse> getCaracteristicas() {
		return caracteristicas;
	}

	public List<OpinioesDetalheResponse> getOpinioes() {
		return opinioes;
	}

	public List<PerguntasDetalheResponse> getPerguntas() {
		return perguntas;
	}

	public List<ImagensDetalheResponse> getImagens() {
		return imagens;
	}

	public CategoriaDetalheResponse getCategoria() {
		return categoria;
	}

}
