package nio.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.DefaultEventLoop;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * @author gjd3
 */
public class NettyClient {
  public static void main(String[] args) throws InterruptedException {
    Bootstrap bootstrap = new Bootstrap();
    EventLoopGroup loopGroup = new DefaultEventLoop();
    bootstrap.group(new NioEventLoopGroup())
        .channel(NioSocketChannel.class)
        .handler(new ChannelInitializer<SocketChannel>() {
          @Override
          protected void initChannel(SocketChannel socketChannel) throws Exception {
            socketChannel.pipeline().addLast(new ChannelInboundHandlerAdapter() {
              @Override
              public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                ByteBuf buf = (ByteBuf) msg;
                System.out.println(">> 接收到客户端发送的数据：" + buf.toString(StandardCharsets.UTF_8));
              }
            });
          }
        });
    Channel channel = bootstrap.connect("localhost", 8080).channel();
    try {
      Scanner scanner = new Scanner(System.in);
      while (true) {
        System.out.println("输入：");
        String line = scanner.nextLine();
        if ("exit".equals(line)) {
          ChannelFuture future = channel.close();
          future.sync();
          break;
        }
        if (line.isEmpty()) {
          continue;
        }
        channel.writeAndFlush(Unpooled.wrappedBuffer(line.getBytes()));
      }
    } finally {
      loopGroup.shutdownGracefully(); //优雅关闭
    }
  }
}
