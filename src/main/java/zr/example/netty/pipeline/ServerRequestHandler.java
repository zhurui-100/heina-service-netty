package zr.example.netty.pipeline;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import zr.example.netty.packet.base.Packet;
import zr.example.netty.task.TaskHandler;

/**
 * @description:
 * @author: zhurui@yinxiang.com
 * @date: 2021/2/7
 * @time: 下午11:52
 */
public class ServerRequestHandler extends ChannelInboundHandlerAdapter {

  private static final Logger LOGGER = LoggerFactory.getLogger(ServerRequestHandler.class);

  @Override
  public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
    ctx.executor().parent().next().submit(new TaskHandler(ctx, (Packet) msg));
  }

}
