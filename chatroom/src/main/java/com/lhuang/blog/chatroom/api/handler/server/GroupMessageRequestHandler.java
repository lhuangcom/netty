package com.lhuang.blog.chatroom.api.handler.server;

import com.lhuang.blog.chatroom.api.protocol.packet.request.GroupMessageRequestPacket;
import com.lhuang.blog.chatroom.api.protocol.packet.response.GroupMessageResponsePacket;
import com.lhuang.blog.chatroom.api.util.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;

/**
 * @author LHuang
 * @since 2019/5/24
 */
@ChannelHandler.Sharable
public class GroupMessageRequestHandler extends SimpleChannelInboundHandler<GroupMessageRequestPacket> {


    public static final GroupMessageRequestHandler INSTANCE = new GroupMessageRequestHandler();

    private GroupMessageRequestHandler() {
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, GroupMessageRequestPacket groupMessageRequestPacket) throws Exception {
        // 1.拿到 groupId 构造群聊消息的响应
        String groupID = groupMessageRequestPacket.getGroupID();
        String message = groupMessageRequestPacket.getMessage();
        GroupMessageResponsePacket groupMessageResponsePacket = new GroupMessageResponsePacket();
        groupMessageResponsePacket.setFromGroupID(groupID);
        groupMessageResponsePacket.setMessage(message);
        groupMessageResponsePacket.setFromUser(SessionUtil.getSession(channelHandlerContext.channel()));

        // 2. 拿到群聊对应的 channelGroup，写到每个客户端
        ChannelGroup channels = SessionUtil.getChannelGroup(groupID);
        channels.writeAndFlush(groupMessageResponsePacket);

    }
}
