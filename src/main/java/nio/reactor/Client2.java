package nio.reactor;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

/**
 * @author gjd3
 */
public class Client2 {
  public static void main(String[] args) {
    try (SocketChannel channel = SocketChannel.open(new InetSocketAddress("localhost", 8080))) {
      Scanner scanner = new Scanner(System.in);
      System.out.println("已连接到服务器");
      while (true) {
        System.out.println("输入发送内容：");
        String line = scanner.nextLine();
        channel.write(ByteBuffer.wrap(line.getBytes()));
        System.out.println("已发送");
        ByteBuffer buffer = ByteBuffer.allocate(128);
        channel.read(buffer);
        buffer.flip();
        System.out.println("服务器发送数据为：" + new String(buffer.array(), 0, buffer.remaining()));
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
