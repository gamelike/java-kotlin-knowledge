package org.springframework.beans.factory.config;

/**
 * 负责记录bean信息的 包含clss类型 方法构造参数 是否为单例等
 * 简化为只包含class
 *
 * @author gjd3
 */
public class BeanDefinition {
  private Class beanClass;

  public BeanDefinition(Class beanClass) {
    this.beanClass = beanClass;
  }

  public Class getBeanClass() {
    return beanClass;
  }

  public void setBeanClass(Class beanClass) {
    this.beanClass = beanClass;
  }
}
