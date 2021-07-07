package br.com.orange.mercadolivre.ml.config.validation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Retention(RUNTIME)
@Target(FIELD)
@Constraint(validatedBy = ExistImpl.class)
public @interface Exist {
	String message() default "Valor inválido";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	Class<?> entity();
}
