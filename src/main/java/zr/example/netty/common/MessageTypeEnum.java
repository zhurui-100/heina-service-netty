package zr.example.netty.common;

import zr.example.netty.handler.*;
import zr.example.netty.packet.*;
import zr.example.netty.packet.base.Packet;

/**
 * @description: 消息类型
 * @author: zhurui@yinxiang.com
 * @date: 2021/2/4
 * @time: 下午6:27
 */
public enum MessageTypeEnum {

  // 消息
  Empty((byte) 0, EmptyPacket.class),
  // 心跳数据包
  Ping((byte) 1, PingPacket.class, PingPacketHandler.class),
  Pong((byte) 2, PongPacket.class),
  // 通用应答数据包
  Reply((byte) 3, ReplyPacket.class),
  // 语音业务数据包
  YuYin((byte) 11, YuYinPacket.class, YuYinPacketHandler.class),
  ;

  private byte code;
  private Class<? extends Packet> packet;
  private Class<? extends PacketHandler> handler;

  MessageTypeEnum(byte code, Class<? extends Packet> packet) {
    this.code = code;
    this.packet = packet;
  }

  MessageTypeEnum(byte code, Class<? extends Packet> packet, Class<? extends PacketHandler> handler) {
    this.code = code;
    this.packet = packet;
    this.handler = handler;
  }

  public byte getCode() {
    return code;
  }

  public Class<? extends Packet> getPacket() {
    return packet;
  }

  public Class<? extends PacketHandler> getHandler() {
    return handler;
  }

  public static MessageTypeEnum getType(byte code) {
    for(MessageTypeEnum c : MessageTypeEnum.values()) {
      if(c.code == code) {
        return c;
      }
    }
    return null;
  }

}
