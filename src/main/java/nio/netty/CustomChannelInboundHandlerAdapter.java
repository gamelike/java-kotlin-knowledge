package nio.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.StandardCharsets;

/**
 * @author gjd3
 * 重写读取方法
 */
public class CustomChannelInboundHandlerAdapter extends ChannelInboundHandlerAdapter {
  @Override
  public void channelRead(ChannelHandlerContext ctx, Object msg) {
    ByteBuf buf = (ByteBuf) msg;
    System.out.println(Thread.currentThread().getName() + "》》 接收到用户发送数据：" + buf.toString(StandardCharsets.UTF_8));
    //通过上下文发送数据 需要flush
    ctx.writeAndFlush(Unpooled.wrappedBuffer("服务器收到！".getBytes()));
  }
}
