package com.lhuang.netty.im.server.api.handler;


import com.lhuang.netty.im.common.api.pojo.Session;
import com.lhuang.netty.im.common.api.protocol.packet.request.ListGroupMembersRequestPacket;
import com.lhuang.netty.im.common.api.protocol.packet.response.ListGroupMembersResponsePacket;
import com.lhuang.netty.im.common.api.util.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LHuang
 * @since 2019/5/20
 */
@Slf4j
@ChannelHandler.Sharable
public class ListGroupMembersRequestHandler extends SimpleChannelInboundHandler<ListGroupMembersRequestPacket> {

    public static final ListGroupMembersRequestHandler INSTANCE = new ListGroupMembersRequestHandler();

    private ListGroupMembersRequestHandler() {
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ListGroupMembersRequestPacket listGroupMembersRequestPacket) {

        // 1. 获取群的 ChannelGroup
        String groupId = listGroupMembersRequestPacket.getGroupID();
        ChannelGroup channelGroup = SessionUtil.getChannelGroup(groupId);

        // 2. 遍历群成员的 channel，对应的 session，构造群成员的信息
        List<Session> sessionList = new ArrayList<>();
        for (Channel channel : channelGroup) {
            Session session = SessionUtil.getSession(channel);
            sessionList.add(session);
        }

        // 3. 构建获取成员列表响应写回到客户端
        ListGroupMembersResponsePacket responsePacket = new ListGroupMembersResponsePacket();

        responsePacket.setGroupId(groupId);
        responsePacket.setSessionList(sessionList);
        channelHandlerContext.channel().writeAndFlush(responsePacket);



    }
}
