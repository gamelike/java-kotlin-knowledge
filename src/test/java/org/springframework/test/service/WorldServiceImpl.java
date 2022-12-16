package org.springframework.test.service;

/**
 * @author gjd
 */
public class WorldServiceImpl implements WorldService {

  private String name;

  @Override
  public void explode() {
    System.out.println("this " + name + " is explode function");
  }

  @Override
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
