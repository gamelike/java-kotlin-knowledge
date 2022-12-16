package org.springframework.test.bean;

import lombok.Data;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Autowired;
import org.springframework.context.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author gjd3
 */
@Data
@Component
public class Person implements InitializingBean, DisposableBean {
  @Value("${name}")
  private String name;
  @Value("${age}")
  private int age;

  @Autowired
  private Car car;

  public void customInitMethod() {
    System.out.println("I was born in the method named customInitMethod");
  }

  public void customDestroyMethod() {
    System.out.println("I died in the method named customDestroyMethod");
  }

  @Override
  public void afterPropertiesSet() throws Exception {
    System.out.println("I was born in the method named afterPropertiesSet");
  }

  @Override
  public void destroy() throws Exception {
    System.out.println("I died in the method named destroy");
  }
}
