package com.lhuang.netty.im.server.api.handler;


import com.lhuang.netty.im.common.api.protocol.packet.request.LogoutRequestPacket;
import com.lhuang.netty.im.common.api.protocol.packet.response.LogoutResponsePacket;
import com.lhuang.netty.im.common.api.util.SessionUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;
import io.netty.util.concurrent.Future;


/**
 * @author LHuang
 * @since 2019/5/21
 */
@Slf4j
@ChannelHandler.Sharable
public class LogoutRequestHandler extends SimpleChannelInboundHandler<LogoutRequestPacket> {

    public static final LogoutRequestHandler INSTANCE = new LogoutRequestHandler();

    private LogoutRequestHandler() {
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, LogoutRequestPacket logoutRequestPacket)  {

        SessionUtil.unBindSession(channelHandlerContext.channel());

        LogoutResponsePacket logoutResponsePacket = new LogoutResponsePacket();
        logoutResponsePacket.setSuccesss(true);

        channelHandlerContext.channel().writeAndFlush(logoutResponsePacket).addListener(
                (Future< ? super Void> future) -> {
                    if (future.isDone()){
                        channelHandlerContext.channel().close();
                    }
                }
        );

        log.info("登出成功");





    }
}
