package com.lhuang.blog.chatroom.api.handler.client;

import com.lhuang.blog.chatroom.api.protocol.packet.request.HeartBeatRequestPacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.concurrent.TimeUnit;

/**
 * @author LHuang
 * @since 2019/5/24
 */
public class HeartBeatTimerHandler extends ChannelInboundHandlerAdapter {

    private static final int HEARTBEAT_INTERVAL = 5;


    @Override
    public void channelActive(ChannelHandlerContext channelHandlerContext) throws Exception {
        scheduleSendHeartBeat(channelHandlerContext);
        super.channelActive(channelHandlerContext);

    }

    private void scheduleSendHeartBeat(ChannelHandlerContext channelHandlerContext) {
        channelHandlerContext.executor().schedule(()->{
            if (channelHandlerContext.channel().isActive()){
                channelHandlerContext.channel().writeAndFlush(new HeartBeatRequestPacket());
            }
        },HEARTBEAT_INTERVAL, TimeUnit.SECONDS);

    }
}
