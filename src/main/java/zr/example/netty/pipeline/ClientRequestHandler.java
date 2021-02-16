package zr.example.netty.pipeline;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import zr.example.netty.packet.base.Packet;

/**
 * @description:
 * @author: zhurui@yinxiang.com
 * @date: 2021/2/7
 * @time: 下午11:52
 */
public class ClientRequestHandler extends ChannelInboundHandlerAdapter {

  private static final Logger LOGGER = LoggerFactory.getLogger(ClientRequestHandler.class);

  @Override
  public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
    Packet packet = (Packet) msg;
    LOGGER.info("Client receive packet {}", JSON.toJSONString(packet));
//    Header header = new Header();
//    header.setMessageType(MessageTypeEnum.PING.getCode());
//    header.setSerializationAlgorithm(SerializerAlgorithmEnum.JSON.getCode());
//
//    PingPacket pingPacket = new PingPacket();
//    byte[] body = JSON.toJSONString(pingPacket).getBytes();
//    header.setLength(body.length);
//
//    ByteBuf byteBuf = PooledByteBufAllocator.DEFAULT.directBuffer();
//    byteBuf.writeBytes(JSON.toJSONString(header).getBytes());
//    byteBuf.writeBytes(body);
//
//    ctx.writeAndFlush(byteBuf);
  }
}
