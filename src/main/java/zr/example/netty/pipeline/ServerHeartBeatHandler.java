package zr.example.netty.pipeline;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

/**
 * @description:
 * @author: zhurui@yinxiang.com
 * @date: 2021/2/14
 * @time: 下午9:03
 */
public class ServerHeartBeatHandler extends ChannelInboundHandlerAdapter {

  private static final Logger LOGGER = LoggerFactory.getLogger(ServerHeartBeatHandler.class);

  @Override
  public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
    if(evt instanceof IdleStateEvent) {
      IdleStateEvent event = (IdleStateEvent) evt;
      if(IdleState.READER_IDLE == event.state()) {
        LOGGER.info("Client {} close", ctx.channel().id());
        ctx.channel().close();
      }
    }
    super.userEventTriggered(ctx, evt);
  }

}
