package zr.example.netty.compress;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import zr.example.netty.common.CompressTypeEnum;

/**
 * @description:
 * @author: zhurui@yinxiang.com
 * @date: 2021/2/10
 * @time: 上午9:35
 */
public class GzipCompressor implements Compressor {

  private static final Logger LOGGER = LoggerFactory.getLogger(GzipCompressor.class);

  private static final int SIZE = 1024;

  @Override
  public byte getCompressType() {
    return CompressTypeEnum.GZIP.getCode();
  }

  @Override
  public byte[] compressor(byte[] source) {
    if(Objects.isNull(source)) {
      return null;
    }
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    try(GZIPOutputStream gzip = new GZIPOutputStream(out, SIZE)) {
      gzip.write(source);
      out.flush();
    } catch (IOException e) {
      LOGGER.error("gzip compress fail", e);
      return null;
    }
    return out.toByteArray();
  }

  @Override
  public byte[] decompress(byte[] bytes) {
    try(ByteArrayOutputStream out = new ByteArrayOutputStream()) {
      try(GZIPInputStream gzip = new GZIPInputStream(new ByteArrayInputStream(bytes), SIZE)) {
        int n;
        byte[] buffer = new byte[SIZE];
        while ((n = gzip.read(buffer)) != -1) {
          out.write(buffer, 0, n);
        }
        out.flush();
        return out.toByteArray();
      }
    } catch (IOException e) {
      LOGGER.error("gzip decompress fail", e);
      return null;
    }
  }

}
