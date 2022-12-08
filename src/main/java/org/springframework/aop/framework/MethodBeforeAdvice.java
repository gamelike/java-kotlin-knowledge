package org.springframework.aop.framework;

import java.lang.reflect.Method;

/**
 * @author gjd3
 */
public interface MethodBeforeAdvice extends BeforeAdvice {
  void before(Method method, Object[] args, Object target);
}
