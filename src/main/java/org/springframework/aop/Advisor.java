package org.springframework.aop;

import org.aopalliance.aop.Advice;

/**
 * @author gjd3
 */
public interface Advisor {
  Advice getAdvice();
}
