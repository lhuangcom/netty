package com.lhuang.blog.chatroom.api.handler.server;

import com.lhuang.blog.chatroom.api.protocol.packet.request.CreateGroupRequestPacket;
import com.lhuang.blog.chatroom.api.protocol.packet.response.CreateGroupResponsePacket;
import com.lhuang.blog.chatroom.api.util.IDUtil;
import com.lhuang.blog.chatroom.api.util.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LHuang
 * @since 2019/5/20
 */
@Slf4j
@ChannelHandler.Sharable
public class CreateGroupRequestHandler extends SimpleChannelInboundHandler<CreateGroupRequestPacket> {

    public static final CreateGroupRequestHandler INSTANCE = new CreateGroupRequestHandler();

    private CreateGroupRequestHandler() {
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, CreateGroupRequestPacket createGroupRequestPacket) {
        List<String> userIDs = createGroupRequestPacket.getUserIDs();
        List<String> userNames = new ArrayList<>();

        // 1. 创建一个 channel 分组
        ChannelGroup channels = new DefaultChannelGroup(channelHandlerContext.executor());

        // 2. 筛选出待加入群聊的用户的 channel 和 userName
        for (String userID:userIDs){
            Channel channel = SessionUtil.getChannel(userID);
            if (channel != null){
               channels.add(channel);
               userNames.add(SessionUtil.getSession(channel).getUserName());
            }
        }

        // 3. 创建群聊创建结果的响应
        String groupID = IDUtil.randomID();
        CreateGroupResponsePacket createGroupResponsePacket = new CreateGroupResponsePacket();
        createGroupResponsePacket.setGroupId(groupID);
        createGroupResponsePacket.setUserNameList(userNames);
        createGroupResponsePacket.setSuccess(true);

        // 4. 给每个客户端发送拉群通知
        channels.writeAndFlush(createGroupResponsePacket);
        log.info("群创建成功，id 为[" + createGroupResponsePacket.getGroupId() + "], ");
        log.info("群里面有：" + createGroupResponsePacket.getUserNameList());

        // 5. 保存群组相关的信息
        SessionUtil.bindChannelGroup(groupID,channels);

    }
}
