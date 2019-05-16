package com.lhuang.blog.chatroom.api.handler;

import com.lhuang.blog.chatroom.api.protocol.packet.LoginResponsePacket;
import com.lhuang.blog.chatroom.api.protocol.packet.MessageRequestPacket;
import com.lhuang.blog.chatroom.api.protocol.packet.MessageResponsePacket;
import com.lhuang.blog.chatroom.api.protocol.packet.PacketCode;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * @author LHuang
 * @since 2019/5/15
 */
@Slf4j
public class MessageRequestHandler extends SimpleChannelInboundHandler<MessageRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, MessageRequestPacket messageRequestPacket)  {

        log.info("处理客户端消息请求");

        log.info("服务端收到客户端发来的消息---"+messageRequestPacket.getMessage());

        MessageResponsePacket messageResponsePacket = new MessageResponsePacket();

        messageResponsePacket.setMessage("服务端回复【" + messageRequestPacket.getMessage() + "】");

        channelHandlerContext.channel().writeAndFlush(messageResponsePacket);
    }
}
