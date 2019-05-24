package com.lhuang.blog.chatroom.api.handler.server;

import com.lhuang.blog.chatroom.api.protocol.packet.request.LogoutRequestPacket;
import com.lhuang.blog.chatroom.api.protocol.packet.request.QuitGroupRequestPacket;
import com.lhuang.blog.chatroom.api.protocol.packet.response.LogoutResponsePacket;
import com.lhuang.blog.chatroom.api.protocol.packet.response.QuitGroupResponsePacket;
import com.lhuang.blog.chatroom.api.util.SessionUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * @author LHuang
 * @since 2019/5/21
 */
@Slf4j
@ChannelHandler.Sharable
public class QuitGroupRequestHandler extends SimpleChannelInboundHandler<QuitGroupRequestPacket> {

    public static final QuitGroupRequestHandler INSTANCE = new QuitGroupRequestHandler();

    private QuitGroupRequestHandler() {
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, QuitGroupRequestPacket quitGroupRequestPacket)  {

        String groupID = quitGroupRequestPacket.getGroupID();

        ChannelGroup channels = SessionUtil.getChannelGroup(groupID);

        QuitGroupResponsePacket quitGroupResponsePacket = new QuitGroupResponsePacket();
        quitGroupResponsePacket.setGroupID(groupID);

        if (channels == null){
            log.info("没有这个群组");
            quitGroupResponsePacket.setSuccess(false);
            quitGroupResponsePacket.setReason("没有这个群组");
        }else {
            channels.remove(channelHandlerContext.channel());
            quitGroupResponsePacket.setSuccess(true);
            log.info("退出群组成功");
        }

        channelHandlerContext.channel().writeAndFlush(quitGroupResponsePacket);





    }
}
