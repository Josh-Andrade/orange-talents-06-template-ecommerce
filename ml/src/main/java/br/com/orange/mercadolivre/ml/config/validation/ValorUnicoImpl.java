package br.com.orange.mercadolivre.ml.config.validation;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.stereotype.Component;

@Component
public class ValorUnicoImpl implements ConstraintValidator<ValorUnico, String> {

	private String entity;
	private String campo;
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void initialize(ValorUnico constraintAnnotation) {
		this.entity = constraintAnnotation.entity();
		this.campo = constraintAnnotation.campo();
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		List<?> results = new ArrayList<>();
		
		Query query = entityManager.createQuery("SELECT e FROM " + entity + " e WHERE e." + campo + " =:value");
		query.setParameter("value", value);
		results = query.getResultList();

		return results.isEmpty();
	}

}
