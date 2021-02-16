package zr.example.netty.compress;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Objects;

import net.jpountz.lz4.LZ4BlockInputStream;
import net.jpountz.lz4.LZ4BlockOutputStream;
import net.jpountz.lz4.LZ4Compressor;
import net.jpountz.lz4.LZ4Factory;
import net.jpountz.lz4.LZ4FastDecompressor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import zr.example.netty.common.CompressTypeEnum;

/**
 * @description:
 * @author: zhurui@yinxiang.com
 * @date: 2021/2/10
 * @time: 上午9:35
 */
public class Lz4Compressor implements Compressor {

  private static final Logger LOGGER = LoggerFactory.getLogger(Lz4Compressor.class);

  private static final int BLOCK = 64 * 1024;

  @Override
  public byte getCompressType() {
    return CompressTypeEnum.LZ4.getCode();
  }

  @Override
  public byte[] compressor(byte[] source) {
    if(Objects.isNull(source)) {
      return null;
    }
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    LZ4Compressor compressor = LZ4Factory.fastestInstance().fastCompressor();
    try(LZ4BlockOutputStream blockOutputStream = new LZ4BlockOutputStream(out, BLOCK, compressor)) {
      blockOutputStream.write(source);
      blockOutputStream.finish();
    } catch (IOException e) {
      LOGGER.error("lz4 compress error", e);
      return null;
    }
    return out.toByteArray();
  }

  @Override
  public byte[] decompress(byte[] bytes) {
    try(ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
      LZ4FastDecompressor fastDecompressor = LZ4Factory.fastestInstance().fastDecompressor();
      try(LZ4BlockInputStream inputStream = new LZ4BlockInputStream(new ByteArrayInputStream(bytes), fastDecompressor)) {
        int count;
        byte[] buffer = new byte[BLOCK];
        while ((count = inputStream.read(buffer)) != -1) {
          outputStream.write(buffer, 0, count);
        }
        outputStream.flush();
        return outputStream.toByteArray();
      }
    } catch (IOException e) {
      LOGGER.error("lz4 decompress error", e);
      return null;
    }
  }

}
