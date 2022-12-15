package org.springframework.context.annotation;

import cn.hutool.core.util.ClassUtil;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.stereotype.Component;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * 扫描指定包下所有的指定注解
 * @author gjd
 */
public class ClassPathScanningCandidateComponentProvider {
  public Set<BeanDefinition> findCandiateComponents(String basePackages) {
    Set<BeanDefinition> candidates = new LinkedHashSet<>();
    Set<Class<?>> classSet = ClassUtil.scanPackageByAnnotation(basePackages, Component.class);
    for (Class<?> clazz : classSet) {
      BeanDefinition beanDefinition = new BeanDefinition(clazz);
      candidates.add(beanDefinition);
    }
    return candidates;
  }
}
