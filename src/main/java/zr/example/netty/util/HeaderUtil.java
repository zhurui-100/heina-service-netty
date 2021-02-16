package zr.example.netty.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.buffer.ByteBuf;
import zr.example.netty.common.MessageTypeEnum;
import zr.example.netty.common.RequestTypeEnum;
import zr.example.netty.model.Header;

/**
 * @description:
 * @author: zhurui@yinxiang.com
 * @date: 2021/2/4
 * @time: 下午3:14
 */
public class HeaderUtil {

  private static final Logger LOGGER = LoggerFactory.getLogger(HeaderUtil.class);

  /**
   * 编码
   *
   * @param byteBuf
   * @param header
   */
  public static void encode(ByteBuf byteBuf, Header header) {
    byteBuf.writeInt(header.getMagic());
    byteBuf.writeByte(header.getVersion());
    byteBuf.writeByte(header.getRequestType());
    byteBuf.writeByte(header.getMessageType());
    byteBuf.writeByte(header.getSerializationAlgorithm());
    byteBuf.writeByte(header.getCompress());
    byteBuf.writeBytes(header.getExtra());
  }

  /**
   * 解码
   *
   * @param byteBuf
   * @return
   */
  public static Header decode(ByteBuf byteBuf) {
    Header header = new Header();
    header.setMagic(byteBuf.readInt());
    header.setVersion(byteBuf.readByte());
    header.setRequestType(byteBuf.readByte());
    header.setMessageType(byteBuf.readByte());
    header.setSerializationAlgorithm(byteBuf.readByte());
    header.setCompress(byteBuf.readByte());
    byteBuf.readBytes(header.getExtra());
    return header;
  }

  /**
   * 获取header
   *
   * @param messageType
   * @return
   */
  public static Header getHeader(byte messageType) {
    Header header = new Header();
    header.setMessageType(messageType);
    return header;
  }

}
