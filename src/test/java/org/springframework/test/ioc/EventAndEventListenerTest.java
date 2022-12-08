package org.springframework.test.ioc;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.common.event.CustomEvent;

/**
 * @author gjd3
 */
public class EventAndEventListenerTest {
  @Test
  public void run() {
    ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:event-and-event-listener.xml");
    applicationContext.publishEvent(new CustomEvent(applicationContext));
    applicationContext.registerShutdownHook();
  }
}
