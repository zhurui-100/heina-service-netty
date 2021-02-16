package zr.example.netty.packet;

import zr.example.netty.common.MessageTypeEnum;
import zr.example.netty.common.RequestTypeEnum;
import zr.example.netty.packet.base.BasePacket;
import zr.example.netty.packet.base.Packet;

/**
 * @description:
 * @author: zhurui@yinxiang.com
 * @date: 2021/2/4
 * @t 下午3:14
 */
public class PongPacket extends BasePacket<String> {

  public PongPacket() {
    super("pong");
  }

  @Override
  public Byte messageType() {
    return MessageTypeEnum.Pong.getCode();
  }

}
