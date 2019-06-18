package com.lhuang.netty.im.client.api.handler;

import com.lhuang.netty.im.common.api.protocol.packet.response.MessageResponsePacket;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * @author LHuang
 * @since 2019/5/15
 */
@Slf4j
@ChannelHandler.Sharable
public class MessageResponseHandler extends SimpleChannelInboundHandler<MessageResponsePacket> {

    public static final MessageResponseHandler INSTANCE = new MessageResponseHandler();

    private MessageResponseHandler() {
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, MessageResponsePacket messageResponsePacket)  {
        String fromUserId = messageResponsePacket.getFromUserID();
        String fromUserName = messageResponsePacket.getFromUserName();
        log.info(fromUserId + " : " + fromUserName + " -> " + messageResponsePacket.getMessage());

    }
}
