package zr.example.netty.compress;

import zr.example.netty.common.CompressTypeEnum;

/**
 * @description: 无压缩
 * @author: zhurui@yinxiang.com
 * @date: 2021/2/10
 * @time: 上午9:35
 */
public class NoCompressor implements Compressor {

  @Override
  public byte getCompressType() {
    return CompressTypeEnum.NONE.getCode();
  }

  @Override
  public byte[] compressor(byte[] source) {
    return source;
  }

  @Override
  public byte[] decompress(byte[] bytes) {
    return bytes;
  }

}
