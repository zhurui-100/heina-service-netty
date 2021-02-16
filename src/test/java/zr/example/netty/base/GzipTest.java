package zr.example.netty.base;

import org.junit.Test;

import zr.example.netty.compress.GzipCompressor;

/**
 * @description:
 * @author: zhurui@yinxiang.com
 * @date: 2021/2/12
 * @time: 下午6:26
 */
public class GzipTest {

  @Test
  public void compressor() {
    String str = "xxxxxxxxxxxxxxxxxxx";

    GzipCompressor gzip = new GzipCompressor();
    byte[] bytes = gzip.compressor(str.getBytes());

    bytes = gzip.decompress(bytes);
    str = new String(bytes);
    System.out.println();
  }

}
