package application.infrastructure.aspect;

import application.infrastructure.annotation.EnableFilter;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.hibernate.Filter;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.lang.reflect.Field;

/**
 * @author violet.
 */
@Component
@Aspect
public class FilterAspect {

    @PersistenceContext
    private EntityManager entityManager;

    @Around("@annotation(application.infrastructure.annotation.EnableFilter)")
    public Object doBefore(ProceedingJoinPoint joinPoint) throws Throwable {
        Filter filter = entityManager.unwrap(Session.class).enableFilter("filter");
        EnableFilter annotation = joinPoint.getClass().getAnnotation(EnableFilter.class);
        for (Field field : annotation.getClass().getFields()) {
            filter.setParameter("filterName", field.get(annotation));
        }
        return joinPoint.proceed();
    }

}
