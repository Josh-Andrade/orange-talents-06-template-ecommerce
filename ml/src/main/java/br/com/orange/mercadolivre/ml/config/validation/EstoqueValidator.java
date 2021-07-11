package br.com.orange.mercadolivre.ml.config.validation;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.com.orange.mercadolivre.ml.controller.request.NovaCompraRequest;
import br.com.orange.mercadolivre.ml.domain.Produto;
import br.com.orange.mercadolivre.ml.repository.ProdutoRepository;

@Component
public class EstoqueValidator implements Validator {

	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return NovaCompraRequest.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		if(errors.hasErrors()) {
			return;
		}
		NovaCompraRequest request = (NovaCompraRequest) target;
		
		Optional<Produto> optionalProduto = produtoRepository.findById(request.getIdProduto());
		
		Assert.isTrue(optionalProduto.isPresent(), "Produto não encontrado");
		
		if(optionalProduto.get().getQuantidade() < request.getQuantidade()) {
			errors.rejectValue("quantidade", null, "O produto informado não possui em estoque a quantidade solicitada");
		}
	}

}
