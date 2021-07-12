package br.com.orange.mercadolivre.ml.shared;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import br.com.orange.mercadolivre.ml.controller.response.CompraResponse;
import br.com.orange.mercadolivre.ml.domain.Compra;
import br.com.orange.mercadolivre.ml.dto.PerguntaResponse;

@Component
public class EmailManagerImpl implements EmailManager {

	@Autowired
	private JavaMailSender sender;

	@Override
	public void enviarEmailPerguntaParaVendedor(PerguntaResponse perguntaResponse) {
		SimpleMailMessage email = new SimpleMailMessage();
		email.setTo(perguntaResponse.getEmailVendedor());
		email.setSubject("Nova pergunta no seu produto: " + perguntaResponse.getNomeProduto());
		email.setText(perguntaResponse.getTituloPergunta());
		enviar(email);
	}

	@Override
	public void enviarEmailVendaParaVendedor(CompraResponse compraResponse) {
		SimpleMailMessage email = new SimpleMailMessage();
		email.setTo(compraResponse.getEmailVendedor());
		email.setSubject("Nova venda no seu produto: " + compraResponse.getNomeProduto());
		email.setText("Foi registrada uma venda do seu produto: " + compraResponse.getNomeProduto()
				+ ", Foram compradas: " + compraResponse.getQuantidade() + " unidades, o status atual da venda é: "
				+ compraResponse.getStatus() + " O usuario que realizou a compra é o: "
				+ compraResponse.getIdentificacaoUsuario());
		enviar(email);
	}

	@Override
	public void enviarEmailCompraBemSucedidaParaCliente(Compra compra) {
		SimpleMailMessage email = new SimpleMailMessage();
		email.setTo(compra.getUsuario().getLogin());
		email.setSubject("Pagamento aceito para o produto: " + compra.getProduto().getNome());
		email.setText("O metodo pagamento escolhido foi: " + compra.getGatewayPagamento() + ", Foram compradas: "
				+ compra.getQuantidade() + " unidades, o status atual da venda é: Sucesso"
				+ ", O vendedor do seu produto foi: " + compra.getProduto().getUsuario().getLogin());
		enviar(email);
	}

	@Override
	public void enviarEmailCompraMalSucedidaParaCliente(Compra compra) {
		SimpleMailMessage email = new SimpleMailMessage();
		email.setTo(compra.getUsuario().getLogin());
		email.setSubject("Pagamento foi recusado para o produto: " + compra.getProduto().getNome());
		email.setText("O metodo pagamento escolhido foi: " + compra.getGatewayPagamento()
				+ " o status atual da venda é: Recusado");
		enviar(email);
	}

	/*
	 * para o envio do email ser bem sucedido é necessario informar o email e senha
	 * no application.properties no meu caso tive que habilitar acesso a aplicativos
	 * menos seguro do gmail, para que o envio fosse realizado
	 */
	private void enviar(SimpleMailMessage email) {
		sender.send(email);
	}
}
