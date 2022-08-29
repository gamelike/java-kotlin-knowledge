package org.springframework.test.ioc.service;

/**
 * @author gjd3
 */
public class HelloService {
  public String sayHello() {
    System.out.println("say hello");
    return "hello";
  }
}
