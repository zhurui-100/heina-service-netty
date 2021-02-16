package zr.example.netty.common;

/**
 * @description: 请求方式
 * @author: zhurui@yinxiang.com
 * @date: 2021/2/4
 * @time: 下午6:27
 */
public enum RequestTypeEnum {

  // 消息
  None((byte) 0),
  // 请求消息
  Request((byte) 1),
  // 响应消息
  Response((byte) 2);

  private byte code;

  RequestTypeEnum(byte code) {
    this.code = code;
  }

  public byte getCode() {
    return code;
  }

  public static RequestTypeEnum getType(byte code) {
    for(RequestTypeEnum c : RequestTypeEnum.values()) {
      if(c.code == code) {
        return c;
      }
    }
    return null;
  }

}
