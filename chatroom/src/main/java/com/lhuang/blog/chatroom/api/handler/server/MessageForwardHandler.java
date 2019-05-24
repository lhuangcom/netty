package com.lhuang.blog.chatroom.api.handler.server;

import com.lhuang.blog.chatroom.api.pojo.Session;
import com.lhuang.blog.chatroom.api.protocol.Attributes;
import com.lhuang.blog.chatroom.api.protocol.packet.request.MessageForwardPacket;
import com.lhuang.blog.chatroom.api.protocol.packet.response.MessageResponsePacket;
import com.lhuang.blog.chatroom.api.util.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * @author LHuang
 * @since 2019/5/20
 */
@Slf4j
@ChannelHandler.Sharable
public class MessageForwardHandler extends SimpleChannelInboundHandler<MessageForwardPacket> {

    public static final MessageForwardHandler INSTANCE = new MessageForwardHandler();

    private MessageForwardHandler() {
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, MessageForwardPacket messageForwardPacket) {

        //1.从发送方获取会话消息
        Session session = channelHandlerContext.channel().attr(Attributes.SESSION).get();

        //2.通过消息发送方的会话信息构造要发送的消息
        MessageResponsePacket messageResponsePacket = new MessageResponsePacket();
        messageResponsePacket.setMessage(messageForwardPacket.getMessage());
        messageResponsePacket.setFromUserID(session.getUserID());
        messageResponsePacket.setFromUserName(session.getUserName());

        //3.拿到消息接收方的 channel
        Channel toUserChannel = SessionUtil.getChannel(messageForwardPacket.getToUserID());


        //4.将消息发送给消息接收方
        if (toUserChannel != null && SessionUtil.hasLogin(toUserChannel)){
            toUserChannel.writeAndFlush(messageResponsePacket);
            return;
        }

        log.info("[" + messageForwardPacket.getToUserID() + "] 不在线，发送失败!");









    }
}
