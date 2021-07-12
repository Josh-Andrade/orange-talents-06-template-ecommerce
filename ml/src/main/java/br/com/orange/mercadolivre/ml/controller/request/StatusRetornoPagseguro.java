package br.com.orange.mercadolivre.ml.controller.request;

import javax.validation.constraints.NotNull;

import br.com.orange.mercadolivre.ml.domain.StatusRetorno;

public enum StatusRetornoPagseguro {
	SUCESSO, ERRO;

	@NotNull
	public StatusRetorno definirStatusRetorno() {
		if(this.equals(SUCESSO)) {
			return StatusRetorno.SUCESSO;
		}
		
		return StatusRetorno.ERRO;
	}
}
