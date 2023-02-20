package nio.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOutboundHandler;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import io.netty.channel.DefaultEventLoop;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpRequestEncoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.ipfilter.IpFilterRule;
import io.netty.handler.ipfilter.IpFilterRuleType;
import io.netty.handler.ipfilter.RuleBasedIpFilter;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import lombok.SneakyThrows;

import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

/**
 * @author gjd3
 */
public class NettyServer {
  @SneakyThrows
  public static void main(String[] args) {
    EventLoopGroup bossGroup = new NioEventLoopGroup();
    EventLoopGroup workerGroup = new NioEventLoopGroup();  //还有EpollEventLoopGroup 只支持linux
    EventLoopGroup handlerGroup = new DefaultEventLoop();

    ServerBootstrap bootstrap = new ServerBootstrap(); //服务器引导启动类
    bootstrap.group(bossGroup, workerGroup)
        .channel(NioServerSocketChannel.class) //指定sockerChannel
        .childHandler(new ChannelInitializer<SocketChannel>() {
          @Override
          protected void initChannel(SocketChannel socketChannel) throws Exception {

            socketChannel.pipeline()
                .addLast(new HttpRequestDecoder())
                .addLast(new RuleBasedIpFilter(new IpFilterRule() {
                  @Override
                  public boolean matches(InetSocketAddress inetSocketAddress) {
                    return !inetSocketAddress.getHostName().equals("127.0.0.1"); //使用localhost则无法访问 使用的是ipv6
                  }

                  @Override
                  public IpFilterRuleType ruleType() {
                    return IpFilterRuleType.REJECT;
                  }
                }))
                .addLast(new LoggingHandler(LogLevel.DEBUG))
                .addLast(new HttpObjectAggregator(Integer.MAX_VALUE)) //搞一个聚合器，将内容聚合为一个FullHttpRequest，参数是最大内容长度
                .addLast(new ChannelInboundHandlerAdapter() {
                  @Override
                  public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//                    System.out.println("收到客户端的数据：" + msg.getClass());
                    //处理聚合信息
                    FullHttpRequest request = (FullHttpRequest) msg;
                    System.out.println("浏览器请求地址：" + request.uri());


                    PageResolver pageResolver = PageResolver.getInstance();

                    ctx.channel().writeAndFlush(pageResolver.resolveResource(request.uri())); //把响应体写回去
                    ctx.channel().close(); //一次性请求，关闭
                  }
                })
                .addLast(new HttpResponseEncoder());

//            socketChannel.pipeline()
//                .addLast(new HttpRequestDecoder())
//                .addLast(new HttpObjectAggregator(Integer.MAX_VALUE)) //搞一个聚合器，将内容聚合为一个FullHttpRequest，参数是最大内容长度
//                .addLast(new ChannelInboundHandlerAdapter() {
//                  @Override
//                  public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//                    System.out.println("收到客户端的数据：" + msg.getClass());
//                    //处理聚合信息
//                    FullHttpRequest request = (FullHttpRequest) msg;
//                    System.out.println("浏览器请求地址：" + request.uri());
//
//
//                    FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK);
//                    response.content().writeCharSequence("Hello world,", (StandardCharsets.UTF_8));
//                    ctx.channel().writeAndFlush(response); //把响应体写回去
//                    ctx.channel().close(); //一次性请求，关闭
//                  }
//                })
//                .addLast(new HttpResponseEncoder());
          }

//          @Override
//          protected void initChannel(SocketChannel socketChannel) throws Exception {
//            socketChannel.pipeline()
//                .addLast(new ChannelInboundHandlerAdapter() {
//                  @Override
//                  public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//                    ByteBuf buf = (ByteBuf) msg;
//                    System.out.println("接收到客户端发送的数据：" + buf.toString(StandardCharsets.UTF_8));
//                    Thread.sleep(10000);   //这里我们直接卡10秒假装在处理任务， 会出现异常
//                    ctx.writeAndFlush(Unpooled.wrappedBuffer("已收到！".getBytes()));
//                  }
//                });
//          }

//          @Override
//          protected void initChannel(SocketChannel channel) {
//            channel.pipeline().addLast(new ChannelOutboundHandlerAdapter() {
//                  @Override
//                  public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
//                    System.out.println("1号出站：" + msg);
//                    ctx.writeAndFlush(Unpooled.wrappedBuffer(msg.toString().getBytes()));
//                  }
//                })
//
//                .addLast(new ChannelInboundHandlerAdapter() {
//                  @Override
//                  public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//                    ByteBuf buf = (ByteBuf) msg;
//                    System.out.println("1接收到客户端发送的数据：" + buf.toString(StandardCharsets.UTF_8));
//                    ctx.fireChannelRead(msg);
//                  }
//                })
//                .addLast(new ChannelInboundHandlerAdapter() {
//                  @Override
//                  public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//                    ByteBuf buf = (ByteBuf) msg;
//                    System.out.println("2接收到客户端发送的数据：" + buf.toString(StandardCharsets.UTF_8));
////                    ctx.writeAndFlush("不会吧不会吧，测试");   //这里可以write任何对象  从当前往前找出战
//                    ctx.channel().writeAndFlush("啊对对对"); //或是通过Channel进行write也可以，是从最后往前找出战
//                  }
//                });
//
////                .addLast(new ChannelOutboundHandlerAdapter() {
////                  @Override
////                  public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
////                    System.out.println("2号出站：" + msg);
////                    ctx.write(msg);  //继续write给其他的出站Handler，不然到这里就断了
////                  }
////                });
//          }
        });
    ChannelFuture future = bootstrap.bind(8080);
//    future.sync(); //等待
//    System.out.println("服务器启动状态" + future.isDone());
    //服务器启动后做一些操作

//    future.addListener(new ChannelFutureListener() {
//      @Override
//      public void operationComplete(ChannelFuture channelFuture) throws Exception {
//        System.out.println("服务器启动状态" + future.isDone());
//      }
//    });

  }
}


