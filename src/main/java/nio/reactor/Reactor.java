package nio.reactor;

import java.io.Closeable;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @author gjd3
 */
public class Reactor implements Closeable, Runnable {

  private final ServerSocketChannel serverSocketChannel;
  private final Selector selector;

  public Reactor() throws IOException {
    this.serverSocketChannel = ServerSocketChannel.open();
    this.selector = Selector.open();
  }

  @Override
  public void close() throws IOException {
    serverSocketChannel.close();
    selector.close();
  }

  @Override
  public void run() {
    try {
      serverSocketChannel.bind(new InetSocketAddress(8080));
      serverSocketChannel.configureBlocking(false);
//      serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT, new Acceptor(serverSocketChannel, selector));// 确保选择器可以选择到对象
      serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT, new Acceptor(serverSocketChannel));// 不适用主acator的selector,在 subreactor中自行创建，此seleactor只负责接受连接
      while (true) {
        int count = selector.select();
        System.out.println("监听到" + count + "个事件");
        Set<SelectionKey> selectionKeys = selector.selectedKeys();
        Iterator<SelectionKey> iterator = selectionKeys.iterator();
        while (iterator.hasNext()) {
          this.dispatch(iterator.next()); // dispatch 分发
          iterator.remove();
        }
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  private void dispatch(SelectionKey key) {
    Object att = key.attachment(); //获取到attachment,serversockerChannel 和对应的客户端
    if (att instanceof Runnable) {
      ((Runnable) att).run(); // 启动现成
    }
  }
}
