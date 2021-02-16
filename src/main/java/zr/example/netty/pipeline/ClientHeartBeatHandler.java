package zr.example.netty.pipeline;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import zr.example.netty.packet.PingPacket;

/**
 * @description:
 * @author: zhurui@yinxiang.com
 * @date: 2021/2/14
 * @time: 下午9:03
 */
public class ClientHeartBeatHandler extends ChannelInboundHandlerAdapter {

  private static final Logger LOGGER = LoggerFactory.getLogger(ClientHeartBeatHandler.class);

  @Override
  public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
    if(evt instanceof IdleStateEvent) {
      IdleStateEvent event = (IdleStateEvent) evt;
      if(IdleState.WRITER_IDLE == event.state()) {
        LOGGER.info("Send heart beat packet. time:{}", System.currentTimeMillis());
        PingPacket packet = new PingPacket();
        ctx.writeAndFlush(packet.encode());
      }
    }
    super.userEventTriggered(ctx, evt);
  }

}
