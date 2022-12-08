package org.springframework.aop;

/**
 * @author gjd
 */
public interface ClassFilter {
    boolean matches(Class<?> clazz);
}
