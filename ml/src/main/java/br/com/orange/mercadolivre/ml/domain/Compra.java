package br.com.orange.mercadolivre.ml.domain;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;

import org.springframework.util.Assert;

import br.com.orange.mercadolivre.ml.controller.request.RetornoGatewayRequest;
import br.com.orange.mercadolivre.ml.controller.response.CompraResponse;

@Entity
public class Compra {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@NotNull
	private Produto produto;

	@ManyToOne
	@NotNull
	private Usuario usuario;

	@NotNull
	@Positive
	@Min(value = 1)
	@Column(nullable = false)
	private Integer quantidade;

	@NotNull
	@Min(value = 1)
	@Column(nullable = false)
	private Double valor;

	@NotNull
	@Enumerated(EnumType.STRING)
	private GatewayPagamento gatewayPagamento;

	@NotNull
	@Enumerated(EnumType.STRING)
	private StatusCompra status;

	@PastOrPresent
	@NotNull
	private LocalDateTime dataCompra;

	@OneToMany(mappedBy = "compra", cascade = CascadeType.MERGE)
	private Set<Transacao> transacoes = new HashSet<Transacao>();

	@Deprecated
	public Compra() {
	}

	public Compra(@NotNull Produto produto, @NotNull Usuario usuario, @NotNull @Positive @Min(1) Integer quantidade,
			@NotNull @Min(1) Double valor, @NotNull GatewayPagamento gatewayPagamento) {
		this.produto = produto;
		this.usuario = usuario;
		this.quantidade = quantidade;
		this.valor = valor;
		this.gatewayPagamento = gatewayPagamento;
		this.status = StatusCompra.INICIADA;
		this.dataCompra = LocalDateTime.now();
	}

	public GatewayPagamento getGatewayPagamento() {
		return gatewayPagamento;
	}

	public Long getId() {
		return id;
	}

	public CompraResponse toResponse() {
		return new CompraResponse(produto.getNome(), usuario.getLogin(), produto.getUsuario().getLogin(), valor,
				quantidade, gatewayPagamento, status, dataCompra);
	}

	public void atribuirTransacao(@Valid RetornoGatewayRequest retornoGatewayRequest) {
		Transacao novaTransacao = retornoGatewayRequest.toEntity(this);

		Assert.isTrue(!transacoes.contains(novaTransacao), "Já existe uma transação processada com esse id");

		transacoes.stream().forEach(t -> {
			Assert.isTrue(t.semSucesso(), "Essa compra já possui uma transação realizada com sucesso!");
		});

		transacoes.add(novaTransacao);
	}

	public boolean processadaComSucesso() {
		Set<Transacao> comSucesso = transacoes.stream().filter(Predicate.not(Transacao::semSucesso))
				.collect(Collectors.toSet());
		Assert.isTrue(comSucesso.size() <= 1, "Deu ruim e essa compra possui mais de uma transação com sucesso");
		return !comSucesso.isEmpty();
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public Produto getProduto() {
		return produto;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

}
