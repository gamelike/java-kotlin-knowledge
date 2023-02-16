package nio.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author gjd3
 */
public class NettyServer {
  public static void main(String[] args) {
    EventLoopGroup bossGroup = new NioEventLoopGroup();
    EventLoopGroup workerGroup = new NioEventLoopGroup();  //还有EpollEventLoopGroup 只支持linux

    ServerBootstrap bootstrap = new ServerBootstrap(); //服务器引导启动类
    bootstrap.group(bossGroup, workerGroup)
        .channel(NioServerSocketChannel.class) //指定sockerChannel
        .childHandler(new ChannelInitializer<SocketChannel>() {
          @Override
          protected void initChannel(SocketChannel socketChannel) {
            //获取流水线,添加一个handler处理读写
            socketChannel.pipeline().addLast(new CustomChannelInboundHandlerAdapter());
          }
        });
    bootstrap.bind(8080);
  }
}


