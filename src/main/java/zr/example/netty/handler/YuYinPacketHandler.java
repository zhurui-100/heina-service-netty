package zr.example.netty.handler;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import zr.example.netty.packet.ReplyPacket;
import zr.example.netty.packet.YuYinPacket;

import static zr.example.netty.common.Constants.SUCCESS;

/**
 * @description: 语音包处理
 * @author: zhurui@yinxiang.com
 * @date: 2021/2/14
 * @time: 下午3:12
 */
public class YuYinPacketHandler implements PacketHandler<YuYinPacket, ReplyPacket> {

  private static final Logger LOGGER = LoggerFactory.getLogger(YuYinPacketHandler.class);

  @Override
  public ReplyPacket handler(YuYinPacket request) {
    LOGGER.info("Server receive packet data：{}", new String(request.getData()));
    ReplyPacket answer = new ReplyPacket();
    answer.setData(SUCCESS);
    return answer;
  }

}
