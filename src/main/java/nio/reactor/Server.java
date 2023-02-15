package nio.reactor;

import java.io.IOException;

/**
 * @author gjd3
 */
public class Server {
  public static void main(String[] args) {
    try (Reactor reactor = new Reactor()) {
      reactor.run();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
