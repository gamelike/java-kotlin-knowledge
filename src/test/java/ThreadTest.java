import lombok.SneakyThrows;

/**
 * @author gjd
 */
public class ThreadTest {
  private static boolean isStart = false;

  public static void main(String[] args) {
    new Thread() {
      @SneakyThrows
      @Override
      public void run() {
        sleep(30 * 1000L);
        isStart = true;
      }
    }.start();
  }
}
