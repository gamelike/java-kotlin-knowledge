package org.springframework.context.event;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author gjd3
 */
public class SimpleApplicationEventMulticaster extends AbstractApplicationEventMulticaster {
  public SimpleApplicationEventMulticaster(BeanFactory beanFactory) {
    setBeanFactory(beanFactory);
  }

  @Override
  public void multicastEvent(ApplicationEvent event) {
    //对事件进行广播
    for (ApplicationListener<ApplicationEvent> applicationListener : applicationListeners) {
      if (supportsEvent(applicationListener, event)) {
        applicationListener.onApplicationEvent(event);
      }
    }
  }

  /**
   * 监听器判断是否对此事件感兴趣
   *
   * @param applicationListener
   * @param applicationEvent
   * @return
   */
  private boolean supportsEvent(ApplicationListener<ApplicationEvent> applicationListener, ApplicationEvent applicationEvent) {
    Type type = applicationListener.getClass().getGenericInterfaces()[0];
    Type actualTypeArgument = ((ParameterizedType) type).getActualTypeArguments()[0];
    String className = actualTypeArgument.getTypeName();
    Class<?> eventClassName;
    try {
      eventClassName = Class.forName(className);
    } catch (ClassNotFoundException e) {
      throw new BeansException("wrong event class name: " + className);
    }
    return eventClassName.isAssignableFrom(applicationEvent.getClass());
  }
}
