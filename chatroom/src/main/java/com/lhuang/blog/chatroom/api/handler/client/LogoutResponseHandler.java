package com.lhuang.blog.chatroom.api.handler.client;

import com.lhuang.blog.chatroom.api.protocol.packet.response.LogoutResponsePacket;
import com.lhuang.blog.chatroom.api.util.LoginUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * @author LHuang
 * @since 2019/5/21
 */
@Slf4j
public class LogoutResponseHandler extends SimpleChannelInboundHandler<LogoutResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, LogoutResponsePacket logoutResponsePacket) {
        if (logoutResponsePacket.getSuccesss()){
            LoginUtil.markLoginout(channelHandlerContext.channel());
            log.info("登出成功");
            return;
        }
        log.info("登出失败");


    }
}
