package org.springframework.aop;

/**
 * 切点抽象
 *
 * @author gjd
 */
public interface Pointcut {
    ClassFilter getClassFilter();

    MethodMatcher getMethodMatcher();
}
