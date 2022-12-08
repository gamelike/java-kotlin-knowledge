package org.springframework.test.service;

/**
 * @author gjd
 */
public class WorldServiceImpl implements WorldService {
  @Override
  public void explode() {
    System.out.println("this is explode function");
  }
}
