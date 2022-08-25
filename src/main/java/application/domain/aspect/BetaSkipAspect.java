package application.domain.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @author violet
 * @since 2022/7/28
 */
@Component
@Aspect
@Slf4j
public class BetaSkipAspect {

    @Around("@annotation(application.domain.annotation.Beta)")
    public Object doAround(ProceedingJoinPoint pjp){
        return null;
    }

}
