package br.com.orange.mercadolivre.ml.controller.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import br.com.orange.mercadolivre.ml.config.validation.Exist;
import br.com.orange.mercadolivre.ml.domain.Compra;
import br.com.orange.mercadolivre.ml.domain.GatewayPagamento;
import br.com.orange.mercadolivre.ml.domain.Produto;
import br.com.orange.mercadolivre.ml.domain.Usuario;

public class NovaCompraRequest {

	@Exist(entity = Produto.class, message = "Produto não encontrado")
	@NotNull
	private Long idProduto;

	@NotNull
	@Positive
	@Min(value = 1)
	private Integer quantidade;

	private GatewayPagamento gateway;

	public NovaCompraRequest(@NotNull Long idProduto, @NotNull @Positive @Min(1) Integer quantidade,
			@NotBlank GatewayPagamento gateway) {
		this.idProduto = idProduto;
		this.quantidade = quantidade;
		this.gateway = gateway;
	}

	public Long getIdProduto() {
		return idProduto;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public Compra toEntity(Produto produto, Usuario usuario) {
		return new Compra(produto, usuario, quantidade, produto.getValor() * quantidade, gateway);
	}
}
