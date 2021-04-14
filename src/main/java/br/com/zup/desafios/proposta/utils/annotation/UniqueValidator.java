package br.com.zup.desafios.proposta.utils.annotation;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueValidator implements ConstraintValidator<Unique, Object> {
    private Class<?> clazz;
    private String field;
    private final EntityManager entityManager;

    public UniqueValidator(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void initialize(Unique param) {
        clazz = param.clazz();
        field = param.field();
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        Query query = entityManager.createQuery("select 1 from " + clazz.getName() + " where "+ field + "=:value");
        query.setParameter("value", o);

        return query.getResultList().isEmpty();
    }
}
