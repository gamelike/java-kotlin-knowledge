package web.init;

import static org.springframework.core.Ordered.HIGHEST_PRECEDENCE;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.context.ApplicationListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author gjd
 */
@Component
@Order(HIGHEST_PRECEDENCE + 3)
@Slf4j
public class CurJobRunner implements ApplicationListener<PreJobEvent> {
  @SneakyThrows
  @Override
  public void onApplicationEvent(PreJobEvent event) {
    log.info("前面任务执行完毕，执行当前任务操作");
    run(null);
  }

  //  @Override
  public void run(ApplicationArguments args) throws Exception {
    new Thread() {
      @SneakyThrows
      @Override
      public void run() {
        synchronized (PreJobRunner.isStart) {
          System.out.println("启动中--------");
        }
        log.info("cur job 启动成功");
      }
    }.start();
  }
}
