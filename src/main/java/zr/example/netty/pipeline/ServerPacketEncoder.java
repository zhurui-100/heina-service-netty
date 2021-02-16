package zr.example.netty.pipeline;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import zr.example.netty.packet.base.Packet;

/**
 * @description:
 * @author: zhurui@yinxiang.com
 * @date: 2021/2/13
 * @time: 下午8:13
 */
public class ServerPacketEncoder extends MessageToByteEncoder<Packet>  {

  @Override
  protected void encode(ChannelHandlerContext channelHandlerContext, Packet packet, ByteBuf byteBuf) throws Exception {
    byteBuf.writeBytes(packet.encode());
  }

}
