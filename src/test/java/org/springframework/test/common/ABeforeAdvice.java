package org.springframework.test.common;

import org.springframework.aop.framework.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * @author gjd3
 */
public class ABeforeAdvice implements MethodBeforeAdvice {
  @Override
  public void before(Method method, Object[] args, Object target) {

  }
}
