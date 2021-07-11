package br.com.orange.mercadolivre.ml.shared;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

import br.com.orange.mercadolivre.ml.controller.response.CompraResponse;
import br.com.orange.mercadolivre.ml.dto.PerguntaResponse;

@Component
public class EmailManagerImpl implements EmailManager {

	@Override
	public void enviarEmailPerguntaParaVendedor(PerguntaResponse perguntaResponse) {
		SimpleMailMessage email = new SimpleMailMessage();
		email.setTo(perguntaResponse.getEmailVendedor());
		email.setFrom("Desafio Mercado livre");
		email.setSubject("Nova pergunta no seu produto: " + perguntaResponse.getNomeProduto());
		email.setText(perguntaResponse.getTituloPergunta());
		System.out.println(email.toString());
	}

	@Override
	public void enviarEmailVendaParaVendedor(CompraResponse compraResponse) {
		SimpleMailMessage email = new SimpleMailMessage();
		email.setTo(compraResponse.getEmailVendedor());
		email.setFrom("Desafio Mercado livre");
		email.setSubject("Nova venda no seu produto: " + compraResponse.getNomeProduto());
		email.setText("Foi registrada uma venda do seu produto: " + compraResponse.getNomeProduto()
				+ ", Foram compradas: " + compraResponse.getQuantidade() + " unidades, o status atual da venda é: "
				+ compraResponse.getStatus() + " O usuario que realizou a compra é o: "
				+ compraResponse.getIdentificacaoUsuario());
		System.out.println(email.toString());
	}

}
