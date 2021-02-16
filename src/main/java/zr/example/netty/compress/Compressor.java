package zr.example.netty.compress;

/**
 * @description:
 * @author: zhurui@yinxiang.com
 * @date: 2021/2/10
 * @time: 上午9:35
 */
public interface Compressor {

  /**
   * 压缩类型
   *
   * @return
   */
  byte getCompressType();

  /**
   * 压缩
   *
   * @param source
   * @return
   */
  byte[] compressor(byte[] source);

  /**
   * 解压缩
   *
   * @param bytes
   * @return
   */
  byte[] decompress(byte[] bytes);

}
