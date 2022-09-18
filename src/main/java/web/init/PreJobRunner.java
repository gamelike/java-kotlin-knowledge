package web.init;

import static org.springframework.core.Ordered.HIGHEST_PRECEDENCE;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author gjd
 */
@Component
@Order(HIGHEST_PRECEDENCE + 2)
@Slf4j
//public class PreJobRunner implements ApplicationRunner {
public class PreJobRunner implements ApplicationContextAware {
  public final static Object isStart = new Object();

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    new Thread() {
      @SneakyThrows
      @Override
      public void run() {
        sleep(5  * 1000);
        log.info("pre job 启动成功");
        String applicationName = applicationContext.getApplicationName();
        System.out.println(applicationName);
        applicationContext.publishEvent(new PreJobEvent("发布事件"));
      }
    }.start();
  }

//  @Override
//  public void run(ApplicationArguments args) throws Exception {
//    new Thread() {
//      @SneakyThrows
//      @Override
//      public void run() {
//        synchronized (isStart) {
//          sleep(10 * 1000);
//          log.info("pre job 启动成功");
//        }
//      }
//    }.start();
//  }
}
