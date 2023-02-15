package nio;

import nio.reactor.Reactor;
import org.junit.Test;

import java.io.IOException;

/**
 * @author gjd3
 */
public class ReactorTest {
  @Test
  public void runReactor() {
    try (Reactor reactor = new Reactor()) {
      reactor.run();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
