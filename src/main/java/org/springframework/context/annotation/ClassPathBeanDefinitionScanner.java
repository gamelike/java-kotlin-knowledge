package org.springframework.context.annotation;

import cn.hutool.core.util.StrUtil;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * @author gjd
 */
public class ClassPathBeanDefinitionScanner extends ClassPathScanningCandidateComponentProvider {

  public static final String AUTOWIRED_ANNOTATION_PROCESSOR_BEAN_NAME = "org.springframework.context.annotation.internalAutowiredAnnotationProcessor";
  private BeanDefinitionRegistry registry;

  public ClassPathBeanDefinitionScanner(BeanDefinitionRegistry registry) {
    this.registry = registry;
  }

  public void doScan(String[] basePackages) {
    for (String basePackage : basePackages) {
      Set<BeanDefinition> beanDefinitionSet = findCandiateComponents(basePackage);
      for (BeanDefinition beanDefinition : beanDefinitionSet) {
        String beanScope = resolveBeanScope(beanDefinition);
        if (StrUtil.isNotEmpty(beanScope)) {
          beanDefinition.setScope(beanScope);
        }
        //生成bean名称
        String beanName = determineBeanName(beanDefinition);

        //注册BeanDefinition
        registry.registryBeanDefinition(beanName, beanDefinition);
      }
    }

    //注册处理 autowired和value 的 beanPostProcessor
    registry.registryBeanDefinition(AUTOWIRED_ANNOTATION_PROCESSOR_BEAN_NAME, new BeanDefinition(AutowiredAnnotationBeanPostProcessor.class));
  }


  /**
   * 获取bean 名称
   *
   * @param beanDefinition
   * @return
   */
  private String determineBeanName(BeanDefinition beanDefinition) {
    Class<?> clazz = beanDefinition.getBeanClass();
    Component component = clazz.getAnnotation(Component.class);
    String value = component.value();
    if (StrUtil.isNotEmpty(value)) {
      return value;
    }
    return StrUtil.lowerFirst(clazz.getSimpleName());
  }

  /**
   * 获取作用域
   *
   * @param beanDefinition
   * @return
   */
  private String resolveBeanScope(BeanDefinition beanDefinition) {
    Class<?> clazz = beanDefinition.getBeanClass();
    Scope scope = clazz.getAnnotation(Scope.class);
    if (scope != null) {
      return scope.value();
    }
    return StrUtil.EMPTY;
  }
}
