package org.springframework.beans.factory.xml;

import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.XmlUtil;
import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanReference;
import org.springframework.beans.factory.support.AbstractBeanDefinitionReader;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author gjd
 */
public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader {
  public static final String BEAN_ELEMENT = "bean";
  public static final String PROPERTY_ELEMENT = "property";
  public static final String ID_ATTRIBUTE = "id";
  public static final String NAME_ATTRIBUTE = "name";
  public static final String CLASS_ATTRIBUTE = "class";
  public static final String VALUE_ATTRIBUTE = "value";
  public static final String REF_ATTRIBUTE = "ref";
  public static final String SCOPE_ATTRIBUTE = "scope";

  //对初始化和销毁方法的注入
  public static final String INIT_METHOD_ATTRIBUTE = "init-method";
  public static final String DESTROY_METHOD_ATTRIBUTE = "destroy-method";

  public XmlBeanDefinitionReader(BeanDefinitionRegistry registry) {
    super(registry);
  }

  public XmlBeanDefinitionReader(BeanDefinitionRegistry registry, ResourceLoader resourceLoader) {
    super(registry, resourceLoader);
  }


  @Override
  public void loadBeanDefinitions(String location) throws BeansException {
    Resource resource = getResoureceLoader().getResource(location);
    loadBeanDefinitions(resource);
  }

  @Override
  public void loadBeanDefinitions(Resource resource) throws BeansException {
    try {
      InputStream is = resource.getInputStream();
      try {
        doLoadBeanDefinition(is);
      } finally {
        is.close();
      }
    } catch (IOException e) {
      throw new BeansException("IOExcepiton parsing XML document from " + resource, e);
    }
  }

  private void doLoadBeanDefinition(InputStream is) {
    Document document = XmlUtil.readXML(is);
    Element root = document.getDocumentElement();
    NodeList childNodes = root.getChildNodes();
    //遍历处理
    for (int i = 0; i < childNodes.getLength(); i++) {
      if (BEAN_ELEMENT.equals(childNodes.item(i).getNodeName())) {
        //解析bean标签
        Element bean = (Element) childNodes.item(i);
        String id = bean.getAttribute(ID_ATTRIBUTE);
        String name = bean.getAttribute(NAME_ATTRIBUTE);
        String className = bean.getAttribute(CLASS_ATTRIBUTE);
        String initMethodName = bean.getAttribute(INIT_METHOD_ATTRIBUTE);
        String destroyMethodName = bean.getAttribute(DESTROY_METHOD_ATTRIBUTE);
        String beanScope = bean.getAttribute(SCOPE_ATTRIBUTE);

        Class<?> clazz = null;
        try {
          clazz = Class.forName(className);
        } catch (ClassNotFoundException e) {
          throw new BeansException("Cannot find class [" + className + "]");
        }
        //id优先
        String beanName = StrUtil.isNotEmpty(id) ? id : name;
        if (StrUtil.isEmpty(beanName)) {
          //如果id name都是空 取 类名的 首字符小写
          beanName = StrUtil.lowerFirst(clazz.getSimpleName());
        }

        BeanDefinition beanDefinition = new BeanDefinition(clazz);
        beanDefinition.setInitMethodName(initMethodName);
        beanDefinition.setDestroyMethodName(destroyMethodName);
        if (StrUtil.isNotEmpty(beanScope)) {
          beanDefinition.setScope(beanScope);
        }
        for (int j = 0; j < bean.getChildNodes().getLength(); j++) {
          //遍历子的 属性
          if (bean.getChildNodes().item(j) instanceof Element) {
            if (PROPERTY_ELEMENT.equals(bean.getChildNodes().item(j).getNodeName())) {
              //解析property标签
              Element property = (Element) bean.getChildNodes().item(j);
              String nameAttribute = property.getAttribute(NAME_ATTRIBUTE);
              String valueAttribute = property.getAttribute(VALUE_ATTRIBUTE);
              String refAttribute = property.getAttribute(REF_ATTRIBUTE);

              if (StrUtil.isEmpty(nameAttribute)) {
                throw new BeansException("The name attribute cannot be null or empty");
              }

              Object value = valueAttribute;
              if (StrUtil.isNotEmpty(refAttribute)) {
                //若为空 有循环引用
                value = new BeanReference(refAttribute);
              }
              PropertyValue propertyValue = new PropertyValue(nameAttribute, value);
              beanDefinition.getPropertyValues().addPropertyValue(propertyValue);
            }
          }
        }
        if (getRegistry().containsBeanDefinition(beanName)) {
          //beanName不能重名
          throw new BeansException("Duplicate beanName[" + beanName + "] is not allowed");
        }
        //注册beanDefinition
        getRegistry().registryBeanDefinition(beanName, beanDefinition);
      }
    }
  }

}
