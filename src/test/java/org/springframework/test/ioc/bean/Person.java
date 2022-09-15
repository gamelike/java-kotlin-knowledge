package org.springframework.test.ioc.bean;

import lombok.Data;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * @author gjd3
 */
@Data
public class Person implements InitializingBean, DisposableBean {
  private String name;

  private int age;

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
