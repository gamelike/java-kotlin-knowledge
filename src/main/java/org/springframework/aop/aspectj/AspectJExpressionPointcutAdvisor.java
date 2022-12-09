package org.springframework.aop.aspectj;

import org.aopalliance.aop.Advice;
import org.springframework.aop.Pointcut;
import org.springframework.aop.PointcutAdvisor;

/**
 * aspectJ表达式的advisor
 *
 * @author gjd3
 */
public class AspectJExpressionPointcutAdvisor implements PointcutAdvisor {

  private AspectJExpressionPointcut pointcut;

  private Advice advice;

  private String expression;


  public void setExpression(String expression) {
    this.expression = expression;
    pointcut = new AspectJExpressionPointcut(expression);
  }

  public void setAdvice(Advice advice) {
    this.advice = advice;
  }

  @Override
  public Advice getAdvice() {
    return advice;
  }

  @Override
  public Pointcut getPointcut() {
    if (pointcut == null) {
      pointcut = new AspectJExpressionPointcut(expression);
    }
    return pointcut;
  }
}