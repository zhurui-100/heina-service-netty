package zr.example.netty.packet.base;

import io.netty.buffer.ByteBuf;
import zr.example.netty.common.MessageTypeEnum;
import zr.example.netty.handler.PacketHandler;
import zr.example.netty.model.Header;
import zr.example.netty.util.ByteBufUtil;
import zr.example.netty.util.HeaderUtil;

import java.util.Objects;

import static zr.example.netty.config.Config.PACKET_HANDLER_MAP;

/**
 * @description:
 * @author: zhurui@yinxiang.com
 * @date: 2021/2/4
 * @time: 下午3:14
 */
public abstract class Packet {

  /** 数据包版本号 */
  protected Byte version = 1;

  /**
   * 命令类型
   *
   * @return
   */
  public abstract Byte messageType();

  /**
   * 获取数据包对应的处理类
   *
   * @return
   */
  public PacketHandler<Packet, Packet> handler() {
    PacketHandler<Packet, Packet> handler = PACKET_HANDLER_MAP.get(messageType());
    if(Objects.isNull(handler)) {
      throw new RuntimeException("Packet handler not found. messageType:" + messageType());
    }
    return handler;
  }

  /**
   * 获取数据包对应的header
   *
   * @return
   */
  public Header header() {
    return HeaderUtil.getHeader(messageType());
  }

  /**
   * 编码
   *
   * @return
   */
  public ByteBuf encode() {
    return ByteBufUtil.encode(this);
  }

  public Byte getVersion() {
    return version;
  }

  public void setVersion(Byte version) {
    this.version = version;
  }
}
