package org.springframework.test.ioc;

/**
 * @author gjd3
 */
public class HelloService {
  public String sayHello() {
    System.out.println("say hello");
    return "hello";
  }
}
