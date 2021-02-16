package zr.example.netty.common;

/**
 * @description: 静态常量
 * @author: zhurui@yinxiang.com
 * @date: 2021/2/14
 * @time: 下午1:20
 */
public class Constants {
  /** 魔法数 */
  public static final int MAGIC = 0x88888888;
  /** 最大帧长度 */
  public static final int MAX_FRAME_LENGTH = 1024;
  /** header长度 */
  public static final int HEADER_SIZE = 45;
  /** 报文长度 */
  public static final int LENGTH = 4;

  public static final byte SUCCESS = 1;
  public static final byte FAIL = 0;

}
