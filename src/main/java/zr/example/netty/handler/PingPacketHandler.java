package zr.example.netty.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import zr.example.netty.packet.PingPacket;
import zr.example.netty.packet.PongPacket;

/**
 * @description:
 * @author: zhurui@yinxiang.com
 * @date: 2021/2/14
 * @time: 下午3:12
 */
public class PingPacketHandler implements PacketHandler<PingPacket, PongPacket> {

  private static final Logger LOGGER = LoggerFactory.getLogger(PingPacketHandler.class);

  @Override
  public PongPacket handler(PingPacket request) {
    return new PongPacket();
  }

}
