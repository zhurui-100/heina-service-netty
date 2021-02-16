package zr.example.netty.base;

import org.junit.Test;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;

/**
 * @description:
 * @author: zhurui@yinxiang.com
 * @date: 2021/2/2
 * @time: 下午6:36
 */
public class Demo {

  @Test
  public void test() {
    ByteBuf directByteBuf = PooledByteBufAllocator.DEFAULT.directBuffer(8, 16);

    ByteBuf byteBuf = PooledByteBufAllocator.DEFAULT.buffer(8, 16);
    System.out.println("readerIndex:" + byteBuf.readerIndex() + " readableBytes:" + byteBuf.readableBytes() +
        " writerIndex:" + byteBuf.writerIndex() + " writableBytes:" + byteBuf.writableBytes() + " capacity:" + byteBuf.capacity());
    for(int i=0;i<4;i++) {
      byteBuf.writeBytes("1111".getBytes());
      System.out.println(directByteBuf.markReaderIndex() + " " + directByteBuf.markWriterIndex());
      System.out.println("readerIndex:" + byteBuf.readerIndex() + " readableBytes:" + byteBuf.readableBytes() +
          " writerIndex:" + byteBuf.writerIndex() + " writableBytes:" + byteBuf.writableBytes() + " capacity:" + byteBuf.capacity());
    }
  }

}
