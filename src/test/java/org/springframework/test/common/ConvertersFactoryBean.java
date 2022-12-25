package org.springframework.test.common;

import org.springframework.beans.factory.FactoryBean;

import java.util.HashSet;
import java.util.Set;

/**
 * @author gjd3
 */
public class ConvertersFactoryBean implements FactoryBean<Set<?>> {
  @Override
  public Set<?> getObject() throws Exception {
    HashSet<Object> convertes = new HashSet<>();
    StringToLocalDateConverter stringToLocalDateConverter = new StringToLocalDateConverter("yyyy-MM-dd");
    convertes.add(stringToLocalDateConverter);
    return convertes;
  }

  @Override
  public boolean isSingleton() {
    return true;
  }
}
