package br.com.orange.mercadolivre.ml.domain;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

@Entity
public class Transacao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Valid
	@NotNull
	@ManyToOne
	private Compra compra;

	@NotBlank
	private String idTransacaoGateway;

	@NotNull
	@Enumerated(EnumType.STRING)
	private StatusRetorno status;

	@PastOrPresent
	private LocalDateTime dataTransacao;

	@Deprecated
	public Transacao() {
	}

	public Transacao(@Valid @NotNull @NotBlank String idTransacaoGateway, @NotBlank Compra compra,
			@NotNull StatusRetorno sucesso) {
		this.compra = compra;
		this.idTransacaoGateway = idTransacaoGateway;
		this.status = sucesso;
		this.dataTransacao = LocalDateTime.now();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idTransacaoGateway == null) ? 0 : idTransacaoGateway.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Transacao other = (Transacao) obj;
		if (idTransacaoGateway == null) {
			if (other.idTransacaoGateway != null)
				return false;
		} else if (!idTransacaoGateway.equals(other.idTransacaoGateway))
			return false;
		if (status != other.status)
			return false;
		return true;
	}

	public boolean semSucesso() {
		return !status.equals(StatusRetorno.SUCESSO);
	}

}
