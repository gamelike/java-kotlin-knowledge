package org.springframework.aop.framework.adapter;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.framework.MethodBeforeAdvice;

/**
 * @author gjd3
 */
public class MethodBeforeAdviceInterceptor implements MethodInterceptor {
  private MethodBeforeAdvice advice;

  public MethodBeforeAdviceInterceptor() {
  }

  public MethodBeforeAdviceInterceptor(MethodBeforeAdvice advice) {
    this.advice = advice;
  }

  public void setAdvice(MethodBeforeAdvice advice) {
    this.advice = advice;
  }

  @Override
  public Object invoke(MethodInvocation methodInvocation) throws Throwable {
    //在代理方法执行之前，先执行before advice操作
    this.advice.before(methodInvocation.getMethod(), methodInvocation.getArguments(), methodInvocation.getThis());
    return methodInvocation.proceed();
  }
}
