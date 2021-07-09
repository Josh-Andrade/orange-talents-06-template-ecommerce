package br.com.orange.mercadolivre.ml.dto;

public class PerguntaResponse {
	
	private String tituloPergunta;
	private String emailVendedor;
	private String nomeProduto;
	
	public PerguntaResponse(String tituloPergunta, String emailVendedor, String nomeProduto) {
		this.tituloPergunta = tituloPergunta;
		this.emailVendedor = emailVendedor;
		this.nomeProduto = nomeProduto;
	}

	public String getTituloPergunta() {
		return tituloPergunta;
	}

	public String getEmailVendedor() {
		return emailVendedor;
	}

	public String getNomeProduto() {
		return nomeProduto;
	}
	
	
}
