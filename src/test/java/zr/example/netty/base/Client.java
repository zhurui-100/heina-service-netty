package zr.example.netty.base;

import static zr.example.netty.common.Constants.HEADER_SIZE;
import static zr.example.netty.common.Constants.LENGTH;
import static zr.example.netty.common.Constants.MAX_FRAME_LENGTH;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import zr.example.netty.packet.PingPacket;
import zr.example.netty.packet.YuYinPacket;
import zr.example.netty.pipeline.ClientHeartBeatHandler;
import zr.example.netty.pipeline.ClientRequestHandler;
import zr.example.netty.pipeline.ServerPacketDecoder;
import zr.example.netty.pipeline.ServerPacketEncoder;

import java.nio.charset.StandardCharsets;

/**
 * @description:
 * @author: zhurui@yinxiang.com
 * @date: 2021/2/2
 * @time: 下午6:36
 */
public class Client {

  NioSocketChannel client;

  @Before
  public void init() throws InterruptedException {
    NioEventLoopGroup clientWorker = new NioEventLoopGroup(1);
    Bootstrap bs = new Bootstrap();
    ChannelFuture connect = bs.group(clientWorker)
        .channel(NioSocketChannel.class)
        .handler(new ChannelInitializer<NioSocketChannel>() {
          @Override
          protected void initChannel(NioSocketChannel ch) throws Exception {
            ChannelPipeline p = ch.pipeline();
//            p.addLast(new IdleStateHandler(0, 5, 0, TimeUnit.SECONDS));
            p.addLast(new LengthFieldBasedFrameDecoder(MAX_FRAME_LENGTH, HEADER_SIZE, LENGTH, 0, 0));
            p.addLast(new ServerPacketEncoder());
            p.addLast(new ServerPacketDecoder());
            p.addLast(new ClientRequestHandler());
            p.addLast(new ClientHeartBeatHandler());
          }
        }).connect("localhost", 8000);
    ChannelFuture future = connect.sync();
    client = (NioSocketChannel) future.channel();
  }

  @Test
  public void testSendPing() {
    PingPacket packet = new PingPacket();
    client.writeAndFlush(packet.encode());
  }

  @Test
  public void testSendRequest() {
//    RequestPacket packet = new RequestPacket();
//    packet.setData("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
//    client.writeAndFlush(packet.encode());
  }

  @Test
  public void testSendYuYin() {
    YuYinPacket packet = new YuYinPacket();
    packet.setData("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx".getBytes(StandardCharsets.UTF_8));
    client.writeAndFlush(packet);
  }

  @After
  public void close() throws InterruptedException {
    client.closeFuture().sync();
  }

}
