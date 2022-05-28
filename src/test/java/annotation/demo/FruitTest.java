package annotation.demo;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author gjd
 */
@Slf4j
public class FruitTest {
  @Test
  public void runApple(){
    try {
      Fruit fruit = FruitResolve.loadProperty(Fruit.class);
      log.info("最终结果{}",fruit);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
