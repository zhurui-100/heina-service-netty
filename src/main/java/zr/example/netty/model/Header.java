package zr.example.netty.model;

import static zr.example.netty.common.Constants.MAGIC;

import zr.example.netty.common.CompressTypeEnum;
import zr.example.netty.common.MessageTypeEnum;
import zr.example.netty.common.RequestTypeEnum;
import zr.example.netty.common.SerializerAlgorithmEnum;

/**
 * @description:
 * @author: zhurui@yinxiang.com
 * @date: 2021/2/6
 * @time: 下午10:38
 */
public class Header {

  private int magic = MAGIC;
  /** 协议版本号 */
  private byte version = 1;
  /** 请求方式 */
  private byte requestType = RequestTypeEnum.Request.getCode();
  /** 消息类型 */
  private byte messageType = MessageTypeEnum.Empty.getCode();
  /** 序列化方式 */
  private byte serializationAlgorithm = SerializerAlgorithmEnum.JSON.getCode();
  /** 压缩方式 */
  private byte compress = CompressTypeEnum.NONE.getCode();
  /** 扩展字段36字节 */
  private byte[] extra = new byte[36];

  public int getMagic() {
    return magic;
  }

  public void setMagic(int magic) {
    this.magic = magic;
  }

  public byte getVersion() {
    return version;
  }

  public void setVersion(byte version) {
    this.version = version;
  }

  public byte getRequestType() {
    return requestType;
  }

  public void setRequestType(byte requestType) {
    this.requestType = requestType;
  }

  public byte getMessageType() {
    return messageType;
  }

  public void setMessageType(byte messageType) {
    this.messageType = messageType;
  }

  public byte getSerializationAlgorithm() {
    return serializationAlgorithm;
  }

  public void setSerializationAlgorithm(byte serializationAlgorithm) {
    this.serializationAlgorithm = serializationAlgorithm;
  }

  public byte getCompress() {
    return compress;
  }

  public void setCompress(byte compress) {
    this.compress = compress;
  }

  public byte[] getExtra() {
    return extra;
  }

  public void setExtra(byte[] extra) {
    this.extra = extra;
  }

}
