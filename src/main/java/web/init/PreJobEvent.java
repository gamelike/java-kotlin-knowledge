package web.init;

import org.springframework.context.ApplicationEvent;

/**
 * @author gjd
 */
public class PreJobEvent extends ApplicationEvent {


  public PreJobEvent(Object source) {
    super(source);
  }
}
