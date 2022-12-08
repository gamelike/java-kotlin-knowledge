package org.springframework.test.common;

import org.springframework.aop.framework.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * @author gjd3
 */
public class WorldServiceBeforeAdvice implements MethodBeforeAdvice {
  @Override
  public void before(Method method, Object[] args, Object target) {
    System.out.println("beforeAdvice: 前置方法");
  }
}
