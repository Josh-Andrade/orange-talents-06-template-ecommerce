package br.com.orange.mercadolivre.ml.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;

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
		return new CompraResponse(produto.getNome(), usuario.getLogin(), produto.getUsuario().getLogin(), valor, quantidade, gatewayPagamento, status, dataCompra);
	}

}
