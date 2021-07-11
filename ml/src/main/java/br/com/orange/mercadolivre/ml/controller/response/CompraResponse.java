package br.com.orange.mercadolivre.ml.controller.response;

import java.time.LocalDateTime;

import br.com.orange.mercadolivre.ml.domain.GatewayPagamento;
import br.com.orange.mercadolivre.ml.domain.StatusCompra;

public class CompraResponse {

	private String nomeProduto;
	private String identificacaoUsuario;
	private String emailVendedor;
	private Double valor;
	private Integer quantidade;
	private GatewayPagamento gatewayPagamento;
	private StatusCompra status;
	private LocalDateTime dataCompra;

	public CompraResponse(String nomeProduto, String identificacaoUsuario, String emailVendedor, Double valor,
			Integer quantidade, GatewayPagamento gatewayPagamento, StatusCompra status, LocalDateTime dataCompra) {
		super();
		this.nomeProduto = nomeProduto;
		this.identificacaoUsuario = identificacaoUsuario;
		this.emailVendedor = emailVendedor;
		this.valor = valor;
		this.quantidade = quantidade;
		this.gatewayPagamento = gatewayPagamento;
		this.status = status;
		this.dataCompra = dataCompra;
	}

	public String getEmailVendedor() {
		return emailVendedor;
	}

	public String getNomeProduto() {
		return nomeProduto;
	}

	public String getIdentificacaoUsuario() {
		return identificacaoUsuario;
	}

	public Double getValor() {
		return valor;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public GatewayPagamento getGatewayPagamento() {
		return gatewayPagamento;
	}

	public StatusCompra getStatus() {
		return status;
	}

	public LocalDateTime getDataCompra() {
		return dataCompra;
	}

}
