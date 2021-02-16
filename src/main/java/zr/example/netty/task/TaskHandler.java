package zr.example.netty.task;

import static zr.example.netty.config.Config.PACKET_HANDLER_MAP;

import java.util.Objects;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.channel.ChannelHandlerContext;
import zr.example.netty.common.MessageTypeEnum;
import zr.example.netty.handler.PacketHandler;
import zr.example.netty.packet.base.Packet;

/**
 * @description:
 * @author: zhurui@yinxiang.com
 * @date: 2021/2/14
 * @time: 下午7:29
 */
public class TaskHandler implements Runnable {

  private static final Logger LOGGER = LoggerFactory.getLogger(TaskHandler.class);

  private final ChannelHandlerContext ctx;
  private final Packet request;

  public TaskHandler(ChannelHandlerContext ctx, Packet request) {
    this.ctx = ctx;
    this.request = request;
  }

  @Override
  public void run() {
    PacketHandler<Packet, Packet> handler = PACKET_HANDLER_MAP.get(request.messageType());
    if(Objects.isNull(handler)) {
      LOGGER.info("Message type {} packet handler not found.", request.messageType());
      return;
    }
    Packet result = handler.handler(request);
    ctx.writeAndFlush(result);
  }

}
