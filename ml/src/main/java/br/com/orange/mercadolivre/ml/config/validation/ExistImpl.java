package br.com.orange.mercadolivre.ml.config.validation;

import java.util.Objects;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ExistImpl implements ConstraintValidator<Exist, Long> {

	@PersistenceContext
	private EntityManager entityManager;

	private Class<?> entity;

	@Override
	public void initialize(Exist constraintAnnotation) {
		this.entity = constraintAnnotation.entity();
	}

	@Override
	public boolean isValid(Long value, ConstraintValidatorContext context) {
		Object objeto;
		if (value == null) {
			return true;
		} else {
			objeto = entityManager.find(this.entity, value);
		}
		return !Objects.isNull(objeto);
	}

}
