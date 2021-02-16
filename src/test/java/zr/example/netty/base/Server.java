package zr.example.netty.base;

import static zr.example.netty.common.Constants.HEADER_SIZE;
import static zr.example.netty.common.Constants.LENGTH;
import static zr.example.netty.common.Constants.MAX_FRAME_LENGTH;

import java.util.concurrent.TimeUnit;

import org.junit.Test;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.timeout.IdleStateHandler;
import zr.example.netty.pipeline.ServerHeartBeatHandler;
import zr.example.netty.pipeline.ServerPacketDecoder;
import zr.example.netty.pipeline.ServerPacketEncoder;
import zr.example.netty.pipeline.ServerRequestHandler;

/**
 * @description:
 * @author: zhurui@yinxiang.com
 * @date: 2021/2/2
 * @time: 下午6:36
 */
public class Server {

  @Test
  public void server() {
    int port = 8000;

    EventLoopGroup boss = new NioEventLoopGroup();
    EventLoopGroup worker = new NioEventLoopGroup(10);

    try {
      ServerBootstrap bootstrap = new ServerBootstrap();
      bootstrap.group(boss, worker).channel(NioServerSocketChannel.class);
      bootstrap.option(ChannelOption.SO_BACKLOG, 1024); // 连接数
      bootstrap.childOption(ChannelOption.SO_KEEPALIVE, true); // 长连接
      bootstrap.childHandler(new ChannelInitializer<NioSocketChannel>() {
        @Override
        protected void initChannel(NioSocketChannel socketChannel) throws Exception {
          ChannelPipeline p = socketChannel.pipeline();
          p.addLast(new IdleStateHandler(30, 0, 0, TimeUnit.SECONDS));
          p.addLast(new LengthFieldBasedFrameDecoder(MAX_FRAME_LENGTH, HEADER_SIZE, LENGTH, 0, 0));
          p.addLast(new ServerPacketDecoder());
          p.addLast(new ServerPacketEncoder());
          p.addLast(new ServerRequestHandler());
          p.addLast(new ServerHeartBeatHandler());
        }
      });
      ChannelFuture channelFuture = bootstrap.bind(port).sync();
      if (channelFuture.isSuccess()) {
        System.err.println("启动Netty服务成功，端口号：" + port);
      }
      channelFuture.channel().closeFuture().sync();
    } catch (Exception e) {
      System.err.println("启动Netty服务异常，异常信息：" + e.getMessage());
      e.printStackTrace();
    } finally {
      boss.shutdownGracefully();
      worker.shutdownGracefully();
    }
  }

}
