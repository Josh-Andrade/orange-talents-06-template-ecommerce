package br.com.orange.mercadolivre.ml.controller.request;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.orange.mercadolivre.ml.domain.Compra;
import br.com.orange.mercadolivre.ml.domain.StatusRetorno;
import br.com.orange.mercadolivre.ml.domain.Transacao;

public class RetornoPaypalRequest implements RetornoGatewayRequest{

	@NotBlank
	private String idTransacao;
	@NotNull
	@Min(0)
	@Max(1)
	private int status;
	
	public RetornoPaypalRequest(@NotBlank String idTransacao, @NotNull @Min(0) @Max(1) int status) {
		this.idTransacao = idTransacao;
		this.status = status;
	}
	
	public Transacao toEntity(Compra compra) {
		return new Transacao(idTransacao, compra, definirStatusRetorno());
	}

	private StatusRetorno definirStatusRetorno() {
		return this.status == 0? StatusRetorno.ERRO : StatusRetorno.SUCESSO;
	}

	@Override
	public String getIdTransacao() {
		return idTransacao;
	}
	
}
