package zr.example.netty.util;

import static zr.example.netty.common.SerializerAlgorithmEnum.JDK;
import static zr.example.netty.config.Config.COMPRESSOR_MAP;
import static zr.example.netty.config.Config.PACKET_MAP;
import static zr.example.netty.config.Config.SERIALIZER_MAP;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.buffer.ByteBuf;
import zr.example.netty.compress.Compressor;
import zr.example.netty.model.Header;
import zr.example.netty.packet.base.Packet;
import zr.example.netty.serializer.Serializer;

/**
 * @description:
 * @author: zhurui@yinxiang.com
 * @date: 2021/2/4
 * @time: 下午3:14
 */
public class PacketUtil {

  private static final Logger LOGGER = LoggerFactory.getLogger(PacketUtil.class);

  /**
   * 编码
   *
   * @param byteBuf
   * @param header
   * @param packet
   */
  public static void encode(ByteBuf byteBuf, Header header, Packet packet) {
    Serializer serializer = SERIALIZER_MAP.get(header.getSerializationAlgorithm());
    byte[] body = serializer.serialize(packet);
    LOGGER.info("body compress before bytes length：{}", body.length);
    Compressor compressor = COMPRESSOR_MAP.get(header.getCompress());
    body = compressor.compressor(body);
    LOGGER.info("body compress after bytes length：{}", body.length);
    byteBuf.writeInt(body.length);
    byteBuf.writeBytes(body);
  }

  /**
   * 解码
   *
   * @param byteBuf
   * @param header
   * @return
   */
  public static Packet decode(ByteBuf byteBuf, Header header) {
    // 数据长度
    int length = byteBuf.readInt();
    // 报文内容
    byte[] body = new byte[length];
    byteBuf.readBytes(body);
    LOGGER.info("body decompress before bytes length：{}", body.length);
    // 压缩
    Compressor compressor = getCompressor(header.getCompress());
    body = compressor.decompress(body);
    LOGGER.info("body decompress after bytes length：{}", body.length);
    // 序列化
    Class<? extends Packet> clazz = getMessage(header.getMessageType());
    Serializer serializer = getSerializer(header.getSerializationAlgorithm());
    return serializer.deSerialize(clazz, body);
  }

  private static Class<? extends Packet> getMessage(byte command) {
    return PACKET_MAP.get(command);
  }

  private static Serializer getSerializer(byte serializationAlgorithm) {
    return Optional.ofNullable(SERIALIZER_MAP.get(serializationAlgorithm)).orElse(SERIALIZER_MAP.get(JDK.getCode()));
  }

  private static Compressor getCompressor(byte compressType) {
    return COMPRESSOR_MAP.get(compressType);
  }

}
