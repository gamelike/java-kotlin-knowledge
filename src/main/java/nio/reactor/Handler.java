package nio.reactor;

import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author gjd3
 */
public class Handler implements Runnable {
  private final SocketChannel channel;

  private static final ExecutorService POOL = Executors.newFixedThreadPool(10);

  public Handler(SocketChannel channel) {
    this.channel = channel;
  }

  @Override
  public void run() {
    ByteBuffer buffer = ByteBuffer.allocate(128);
    try {
      channel.read(buffer);
      buffer.flip();
      POOL.submit(() -> {
        System.out.println("接收到客户端数据：" + new String(buffer.array(), 0, buffer.remaining()));
        try {
          Thread.sleep(10000); // 客户端发过来数据会存卡在这个阶段，使用线程池模式
          channel.write(ByteBuffer.wrap("已收到".getBytes()));
        } catch (Exception e) {
          throw new RuntimeException(e);
        }
      });
    } catch (Exception e) {
      throw new RuntimeException(e);
    }

  }
}
