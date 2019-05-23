package com.lhuang.blog.chatroom.api.handler.server;

import com.lhuang.blog.chatroom.api.protocol.packet.request.LogoutRequestPacket;
import com.lhuang.blog.chatroom.api.protocol.packet.response.LogoutResponsePacket;
import com.lhuang.blog.chatroom.api.util.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * @author LHuang
 * @since 2019/5/21
 */
@Slf4j
public class LogoutRequestHandler extends SimpleChannelInboundHandler<LogoutRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, LogoutRequestPacket logoutRequestPacket)  {

        SessionUtil.unBindSession(channelHandlerContext.channel());

        LogoutResponsePacket logoutResponsePacket = new LogoutResponsePacket();
        logoutResponsePacket.setSuccesss(true);

        channelHandlerContext.channel().writeAndFlush(logoutResponsePacket);

        log.info("登出成功");





    }
}
