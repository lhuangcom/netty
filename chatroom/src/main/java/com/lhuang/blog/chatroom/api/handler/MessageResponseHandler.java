package com.lhuang.blog.chatroom.api.handler;

import com.lhuang.blog.chatroom.api.protocol.packet.MessageResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

/**
 * @author LHuang
 * @since 2019/5/15
 */
@Slf4j
public class MessageResponseHandler extends SimpleChannelInboundHandler<MessageResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, MessageResponsePacket messageResponsePacket)  {
        log.info(new Date() + ": 收到服务端的消息: " + messageResponsePacket.getMessage());

    }
}
