package zr.example.netty.common;

import zr.example.netty.compress.Compressor;
import zr.example.netty.compress.GzipCompressor;
import zr.example.netty.compress.Lz4Compressor;
import zr.example.netty.compress.NoCompressor;

/**
 * @description:
 * @author: zhurui@yinxiang.com
 * @date: 2021/2/4
 * @time: 下午6:27
 */
public enum CompressTypeEnum {
  //
  NONE((byte) 0, NoCompressor.class),
  LZ4((byte) 1, Lz4Compressor.class),
  GZIP((byte) 2, GzipCompressor.class),
  ;

  /** 编码 */
  private Byte code;
  /** 类型 */
  private Class<? extends Compressor> clazz;

  CompressTypeEnum(Byte code, Class<? extends Compressor> clazz) {
    this.code = code;
    this.clazz = clazz;
  }

  public byte getCode() {
    return code;
  }

  public Class<? extends Compressor> getClazz() {
    return clazz;
  }

  public static CompressTypeEnum getEnum(Byte value) {
    for(CompressTypeEnum e : CompressTypeEnum.values()) {
      if(e.getCode() == value) {
        return e;
      }
    }
    return NONE;
  }

}
