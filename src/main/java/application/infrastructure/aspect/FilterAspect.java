package application.infrastructure.aspect;

import application.infrastructure.annotation.EnableFilter;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
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
        Session session = entityManager.unwrap(Session.class);
        EnableFilter annotation = ((MethodSignature)(joinPoint.getSignature())).getMethod().getAnnotation(EnableFilter.class);
        Filter filter = session.enableFilter(annotation.filterName());
        filter.setParameter(annotation.name(), annotation.value());
        return joinPoint.proceed();
    }

}
