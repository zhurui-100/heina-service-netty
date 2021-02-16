package zr.example.netty.config;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;

import com.google.common.collect.Maps;

import zr.example.netty.common.CompressTypeEnum;
import zr.example.netty.common.MessageTypeEnum;
import zr.example.netty.common.SerializerAlgorithmEnum;
import zr.example.netty.compress.Compressor;
import zr.example.netty.handler.PacketHandler;
import zr.example.netty.packet.base.Packet;
import zr.example.netty.serializer.Serializer;

/**
 * @description:
 * @author: zhurui@yinxiang.com
 * @date: 2021/2/6
 * @time: 下午9:00
 */
public class Config {

  public static final Map<Byte, Serializer> SERIALIZER_MAP = Maps.newHashMap();
  public static final Map<Byte, Compressor> COMPRESSOR_MAP = Maps.newHashMap();
  public static final Map<Byte, Class<? extends Packet>> PACKET_MAP = Maps.newHashMap();
  public static final Map<Byte, PacketHandler<Packet, Packet>> PACKET_HANDLER_MAP = Maps.newHashMap();

  // 消息类型
  static {
    Arrays.stream(MessageTypeEnum.values()).forEach(s -> {
      try {
        PACKET_MAP.put(s.getCode(), s.getPacket());
        if(Objects.nonNull(s.getHandler())) {
          PACKET_HANDLER_MAP.put(s.getCode(), s.getHandler().newInstance());
        }
      } catch (Exception e) {
        throw new RuntimeException("Packet init fail", e);
      }
    });
  }
  // 序列化
  static {
    Arrays.stream(SerializerAlgorithmEnum.values()).forEach(s -> {
      try {
        SERIALIZER_MAP.put(s.getCode(), s.getClazz().newInstance());
      } catch (Exception e) {
        throw new RuntimeException("Serializer init fail", e);
      }
    });
  }
  // 压缩
  static {
    Arrays.stream(CompressTypeEnum.values()).forEach(s -> {
      try {
        COMPRESSOR_MAP.put(s.getCode(), s.getClazz().newInstance());
      } catch (Exception e) {
        throw new RuntimeException("Compressor init fail", e);
      }
    });
  }

}
