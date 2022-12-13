package org.springframework.beans.factory;

import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.Properties;

/**
 * @author gjd3
 */
public class PropertyPlaceholderConfigurer implements BeanFactoryPostProcessor {

  public static final String PLACEHOLDER_PREFIX = "${";

  public static final String PLACEHOLDER_SUFFIX = "}";

  private String location;

  @Override
  public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
    //加载配置文件
    Properties properties = loadProperties();

    //配置文件属性值替换
    processPropertiess(beanFactory, properties);
  }


  /**
   * 加载配置文件
   *
   * @return
   */
  private Properties loadProperties() {
    try {
      DefaultResourceLoader resourceLoader = new DefaultResourceLoader();
      Resource resource = resourceLoader.getResource(location);
      Properties properties = new Properties();
      properties.load(resource.getInputStream());
      return properties;
    } catch (IOException e) {
      throw new BeansException("Could not load properties!", e);
    }
  }

  /**
   * 属性值替换占位符
   *
   * @param beanFactory
   * @param properties
   */
  private void processPropertiess(ConfigurableListableBeanFactory beanFactory, Properties properties) {
    String[] beanDefinitionNames = beanFactory.getBeanDefinitionNames();
    for (String beanName : beanDefinitionNames) {
      BeanDefinition beanDefinition = beanFactory.getBeanDefinition(beanName);
      resolvePropertyValues(beanDefinition, properties);
    }

  }


  private void resolvePropertyValues(BeanDefinition beanDefinition, Properties properties) {
    PropertyValues propertyValues = beanDefinition.getPropertyValues();
    for (PropertyValue propertyValue : propertyValues.getPropertys()) {
      Object value = propertyValue.getValue();
      if (value instanceof String) {
        //TODO 仅支持一个占位符的格式
        String strVal = (String) value;
        int start = strVal.indexOf(PLACEHOLDER_PREFIX);
        int end = strVal.indexOf(PLACEHOLDER_SUFFIX);
        StringBuffer buf = new StringBuffer(strVal);
        if (start != -1 && end != -1 && start < end) {
          String propKey = strVal.substring(start + 2, end);
          String propVal = properties.getProperty(propKey);
          buf.replace(start, end + 1, propVal);
          //add的时候，如果存在，会覆盖旧的值
          propertyValues.addPropertyValue(new PropertyValue(propertyValue.getName(), buf.toString()));
        }
      }
    }
  }


  public void setLocation(String location) {
    this.location = location;
  }
}
