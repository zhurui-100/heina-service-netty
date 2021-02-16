package zr.example.netty.common;

import zr.example.netty.serializer.FastJsonSerializer;
import zr.example.netty.serializer.JDKSerializer;
import zr.example.netty.serializer.Serializer;

/**
 * @description:
 * @author: zhurui@yinxiang.com
 * @date: 2021/2/4
 * @time: 下午6:27
 */
public enum SerializerAlgorithmEnum {

  // JSON格式序列化
  JSON((byte) 1, FastJsonSerializer.class),
  JDK((byte) 2, JDKSerializer.class),
  ;

  /**  */
  private Byte code;
  private Class<? extends Serializer> clazz;

  SerializerAlgorithmEnum(Byte code, Class<? extends Serializer> clazz) {
    this.code = code;
    this.clazz = clazz;
  }

  public byte getCode() {
    return code;
  }

  public Class<? extends Serializer> getClazz() {
    return clazz;
  }

  public static SerializerAlgorithmEnum getAlgorith(Byte value) {
    for(SerializerAlgorithmEnum e : SerializerAlgorithmEnum.values()) {
      if(e.getCode() == value) {
        return e;
      }
    }
    return null;
  }

}
