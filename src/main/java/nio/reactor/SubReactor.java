package nio.reactor;

import java.io.Closeable;
import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author gjd3
 */
public class SubReactor implements Runnable, Closeable {

  private final Selector selector;

  private static final ExecutorService POOL = Executors.newFixedThreadPool(4);
  private static final SubReactor[] reactors = new SubReactor[4];
  private static int selectIndex = 0;  //轮询机制，每接受一个新连接，轮询

  static {
    for (int i = 0; i < 4; i++) {
      try {
        reactors[i] = new SubReactor();
        POOL.submit(reactors[i]);
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }
  }

  public SubReactor() throws IOException {
    selector = Selector.open();
  }

  public static Selector nextSelector() {
    Selector selector = reactors[selectIndex].selector;
    selectIndex = (selectIndex + 1) % 4;
    return selector;
  }

  @Override
  public void close() throws IOException {
    selector.close();
  }

  @Override
  public void run() {
    while (true) {
      try {
        int count = selector.select();
        System.out.println(Thread.currentThread().getName() + ">> 监听到：" + count + " 个连接");
        Set<SelectionKey> selectionKeys = selector.selectedKeys();
        Iterator<SelectionKey> iterator = selectionKeys.iterator();
        while (iterator.hasNext()) {
          this.dispatch(iterator.next());
          iterator.remove();
        }
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }
  }

  private void dispatch(SelectionKey key) {
    Object att = key.attachment();
    if (att instanceof Runnable) {
      ((Runnable) att).run();
    }

  }
}
