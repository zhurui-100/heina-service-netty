package zr.example.netty.packet;

import zr.example.netty.common.MessageTypeEnum;
import zr.example.netty.common.RequestTypeEnum;
import zr.example.netty.packet.base.BasePacket;
import zr.example.netty.packet.base.Packet;

/**
 * @description:
 * @author: zhurui@yinxiang.com
 * @date: 2021/2/4
 * @time: 下午3:14
 */
public class PingPacket extends BasePacket<String> {

  public PingPacket() {
    super("ping");
  }

  @Override
  public Byte messageType() {
    return MessageTypeEnum.Ping.getCode();
  }

}
