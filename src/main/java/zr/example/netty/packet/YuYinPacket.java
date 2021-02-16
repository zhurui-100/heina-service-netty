package zr.example.netty.packet;

import zr.example.netty.common.CompressTypeEnum;
import zr.example.netty.common.MessageTypeEnum;
import zr.example.netty.common.RequestTypeEnum;
import zr.example.netty.model.Header;
import zr.example.netty.packet.base.BasePacket;
import zr.example.netty.packet.base.Packet;

/**
 * @description: 语音包
 * @author: zhurui@yinxiang.com
 * @date: 2021/2/4
 * @time: 下午3:14
 */
public class YuYinPacket extends BasePacket<byte[]> {

  @Override
  public Byte messageType() {
    return MessageTypeEnum.YuYin.getCode();
  }

  @Override
  public Header header() {
    Header header = super.header();
    header.setCompress(CompressTypeEnum.GZIP.getCode());
    return header;
  }
}
