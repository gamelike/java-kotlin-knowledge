package nio.reactor;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * @author gjd3
 */
public class Acceptor implements Runnable {

  private final ServerSocketChannel serverSocketChannel;

//  private final Selector selector;

//  public Acceptor(ServerSocketChannel serverSocketChannel, Selector selector) {
//    this.serverSocketChannel = serverSocketChannel;
//    this.selector = selector;
//  }


  public Acceptor(ServerSocketChannel serverSocketChannel) {
    this.serverSocketChannel = serverSocketChannel;
  }

  @Override
  public void run() {
    try {
      SocketChannel channel = serverSocketChannel.accept();
//      System.out.println("客户端已连接，ip 是：" + channel.getRemoteAddress());
      System.out.println(Thread.currentThread().getName()+">> 客户端已连接，ip 是：" + channel.getRemoteAddress());
      channel.configureBlocking(false); //配置不阻塞
      Selector selector = SubReactor.nextSelector(); //主动去获取seleactor
      selector.wakeup(); //注册前唤醒防止卡死
      channel.register(selector, SelectionKey.OP_READ, new Handler(channel)); //注册时候，创建handler，可以用来负责接受数据
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

  }
}
