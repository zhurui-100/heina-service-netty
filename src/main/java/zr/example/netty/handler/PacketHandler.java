package zr.example.netty.handler;

import zr.example.netty.packet.base.Packet;

/**
 * @description:
 * @author: zhurui@yinxiang.com
 * @date: 2021/2/14
 * @time: 下午3:24
 */
public interface PacketHandler<T extends Packet, M extends Packet> {

  /**
   * 处理类
   *
   * @param packet
   * @return
   */
  M handler(T packet);

}
