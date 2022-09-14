package org.springframework.context.support;

import org.springframework.beans.BeansException;

/**
 * xml上下文加载
 *
 * @author gjd
 */
public class ClassPathXmlApplicationContext extends AbstractXmlApplicationContext {
  private String[] configLocations;


  public ClassPathXmlApplicationContext(String configLocation) {
    this(new String[]{configLocation});
  }

  /**
   * 从xml加载上下文 并且刷新
   *
   * @param configLocations
   */
  public ClassPathXmlApplicationContext(String[] configLocations) {
    this.configLocations = configLocations;
    refresh();
  }


  @Override
  public String[] getConfigLocations() {
    return configLocations;
  }

}
