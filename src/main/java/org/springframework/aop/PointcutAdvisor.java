package org.springframework.aop;

/**
 * @author gjd3
 */
public interface PointcutAdvisor extends Advisor {
  Pointcut getPointcut();
}
