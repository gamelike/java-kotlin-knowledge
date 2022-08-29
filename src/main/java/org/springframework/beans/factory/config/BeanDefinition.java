package org.springframework.beans.factory.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.PropertyValues;

/**
 * 负责记录bean信息的 包含clss类型 方法构造参数 是否为单例等
 * 简化为只包含class
 *
 * @author gjd3
 */
@Data
@AllArgsConstructor
public class BeanDefinition {
  private Class beanClass;

  private PropertyValues propertyValues;

  public BeanDefinition(Class beanClass) {
    this.beanClass = beanClass;
  }

  public PropertyValues getPropertyValues() {
    return propertyValues;
  }

  public void setPropertyValues(PropertyValues propertyValues) {
    this.propertyValues = propertyValues;
  }

  public Class getBeanClass() {
    return beanClass;
  }

  public void setBeanClass(Class beanClass) {
    this.beanClass = beanClass;
  }
}
