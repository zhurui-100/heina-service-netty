package zr.example.netty.util;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import zr.example.netty.model.Header;
import zr.example.netty.packet.base.Packet;

/**
 * @description:
 * @author: zhurui@yinxiang.com
 * @date: 2021/2/14
 * @time: 下午12:59
 */
public class ByteBufUtil {

  private static final Logger LOGGER = LoggerFactory.getLogger(ByteBufUtil.class);

  /**
   * 解析字节数据
   *
   * @param packet
   * @return
   */
  public static ByteBuf encode(Packet packet) {
    ByteBuf byteBuf = PooledByteBufAllocator.DEFAULT.directBuffer();
    Header header = packet.header();
    HeaderUtil.encode(byteBuf, header);
    PacketUtil.encode(byteBuf, header, packet);
    LOGGER.info("encode total bytes length {}", byteBuf.readableBytes());
    return byteBuf;
  }

}
