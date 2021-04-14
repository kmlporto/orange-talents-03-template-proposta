package br.com.zup.desafios.proposta.utils.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = UniqueValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Unique {
    String message() default "Esse valor deve ser único";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    Class<?> clazz();

    String field();
}
