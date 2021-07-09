package br.com.orange.mercadolivre.ml.shared;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

import br.com.orange.mercadolivre.ml.dto.PerguntaResponse;

@Component
public class EmailManagerImpl implements EmailManager{

	
	@Override
	public void EnviarEmailParaVendedor(PerguntaResponse perguntaResponse) {
		SimpleMailMessage email = new SimpleMailMessage();
		email.setTo(perguntaResponse.getEmailVendedor());
		email.setFrom("Desafio Mercado livre");
		email.setSubject("Nova pergunta no seu produto: " + perguntaResponse.getNomeProduto());
		email.setText(perguntaResponse.getTituloPergunta());
		System.out.println(email.toString());
	}

}
