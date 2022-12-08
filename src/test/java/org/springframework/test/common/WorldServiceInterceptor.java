package org.springframework.test.common;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * @author gjd
 */
public class WorldServiceInterceptor implements MethodInterceptor {
  @Override
  public Object invoke(MethodInvocation methodInvocation) throws Throwable {
    System.out.println("do sth before the earth explodes");
    Object result = methodInvocation.proceed();
    System.out.println("do sth after the earth explodes");
    return result;
  }
}
