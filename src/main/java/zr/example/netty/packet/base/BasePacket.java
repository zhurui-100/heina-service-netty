package zr.example.netty.packet.base;

/**
 * @description:
 * @author: zhurui@yinxiang.com
 * @date: 2021/2/4
 * @time: 下午3:14
 */
public abstract class BasePacket<T> extends Packet {

  protected BasePacket() {
  }

  protected BasePacket(T data) {
    this.data = data;
  }

  private T data;

  public T getData() {
    return data;
  }

  public void setData(T data) {
    this.data = data;
  }

}
