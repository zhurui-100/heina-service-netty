package zr.example.netty.packet;

import zr.example.netty.common.MessageTypeEnum;
import zr.example.netty.common.RequestTypeEnum;
import zr.example.netty.packet.base.BasePacket;
import zr.example.netty.packet.base.Packet;

/**
 * @description: 空消息
 * @author: zhurui@yinxiang.com
 * @date: 2021/2/14
 * @time: 下午11:08
 */
public class EmptyPacket extends BasePacket<Void> {

  @Override
  public Byte messageType() {
    return MessageTypeEnum.Empty.getCode();
  }

}
