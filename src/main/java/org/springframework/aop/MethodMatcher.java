package org.springframework.aop;

import java.lang.reflect.Method;

/**
 * @author gjd
 */
public interface MethodMatcher {
    boolean matches(Method method, Class<?> targetClass);
}
