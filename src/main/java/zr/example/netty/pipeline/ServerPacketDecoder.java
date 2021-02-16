package zr.example.netty.pipeline;

import static zr.example.netty.common.Constants.MAGIC;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import zr.example.netty.model.Header;
import zr.example.netty.packet.base.Packet;
import zr.example.netty.util.HeaderUtil;
import zr.example.netty.util.PacketUtil;

public class ServerPacketDecoder extends ByteToMessageDecoder {

  private static final Logger LOGGER = LoggerFactory.getLogger(ServerPacketDecoder.class);

  @Override
  protected void decode(ChannelHandlerContext ctx, ByteBuf byteBuf, List<Object> list) throws Exception {
    Header header = HeaderUtil.decode(byteBuf);
    if(header.getMagic() != MAGIC) {
      LOGGER.info("Client {} magic {} error.", ctx.channel().id(), header.getMagic());
      ctx.close();
    }
    Packet packet = PacketUtil.decode(byteBuf, header);
    list.add(packet);
  }

}