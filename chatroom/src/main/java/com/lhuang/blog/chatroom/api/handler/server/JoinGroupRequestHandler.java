package com.lhuang.blog.chatroom.api.handler.server;

import com.lhuang.blog.chatroom.api.protocol.packet.request.JoinGroupRequestPacket;
import com.lhuang.blog.chatroom.api.protocol.packet.response.JoinGroupResponsePacket;
import com.lhuang.blog.chatroom.api.util.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import lombok.extern.slf4j.Slf4j;

/**
 * @author LHuang
 * @since 2019/5/23
 */
@Slf4j
public class JoinGroupRequestHandler extends SimpleChannelInboundHandler<JoinGroupRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, JoinGroupRequestPacket joinGroupRequestPacket) throws Exception {


        String groupId = joinGroupRequestPacket.getGroupID();

        ChannelGroup channels = SessionUtil.getChannelGroup(groupId);

        JoinGroupResponsePacket joinGroupResponsePacket = new JoinGroupResponsePacket();

        joinGroupResponsePacket.setGroupID(groupId);

        if (channels == null){
            log.info("没有这个群组");
            joinGroupResponsePacket.setSuccess(false);
            joinGroupResponsePacket.setReason("没有这个群组");
        }else {
            channels.add(channelHandlerContext.channel());
            joinGroupResponsePacket.setSuccess(true);
            log.info("加入群组成功");
        }


        channelHandlerContext.channel().writeAndFlush(joinGroupResponsePacket);




    }
}
