package application.infrastructure.aspect;

import application.infrastructure.annotation.EnableFilter;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
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
        EnableFilter annotation = joinPoint.getClass().getAnnotation(EnableFilter.class);
        Class<? extends EnableFilter> aClass = annotation.getClass();
        Field name = aClass.getField("filterName");
        Filter filter = session.enableFilter(name.get(annotation).toString());
        filter.setParameter(aClass.getField("name").get(annotation).toString(),
                aClass.getField("value").get(annotation));
        return joinPoint.proceed();
    }

}
