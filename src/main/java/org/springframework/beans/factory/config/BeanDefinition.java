package org.springframework.beans.factory.config;

import lombok.Data;
import org.springframework.beans.PropertyValues;

/**
 * 负责记录bean信息的 包含clss类型 方法构造参数 是否为单例等
 * 简化为只包含class
 *
 * @author gjd3
 */
@Data
public class BeanDefinition {

  private static String SCOPE_SINGLETON = "singleton";
  private static String SCOPE_PROTOTYPE = "prototype";
  private Class beanClass;

  private PropertyValues propertyValues;

  private String initMethodName;

  private String destroyMethodName;

  private String scope = SCOPE_SINGLETON;
  private boolean singleton = true;
  private boolean prototype = false;

  public void setScope(String scope) {
    this.scope = scope;
    this.singleton = SCOPE_SINGLETON.equals(scope);
    this.prototype = SCOPE_PROTOTYPE.equals(scope);
  }

  public boolean isSingleton() {
    return singleton;
  }

  public boolean isPrototype() {
    return prototype;
  }

  public BeanDefinition(Class beanClass, PropertyValues propertyValues) {
    this.beanClass = beanClass;
    this.propertyValues = propertyValues != null ? propertyValues : new PropertyValues();
  }

  public BeanDefinition(Class beanClass) {
    this(beanClass, null);
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

  public String getInitMethodName() {
    return initMethodName;
  }

  public void setInitMethodName(String initMethodName) {
    this.initMethodName = initMethodName;
  }

  public String getDestroyMethodName() {
    return destroyMethodName;
  }

  public void setDestroyMethodName(String destroyMethodName) {
    this.destroyMethodName = destroyMethodName;
  }
}
