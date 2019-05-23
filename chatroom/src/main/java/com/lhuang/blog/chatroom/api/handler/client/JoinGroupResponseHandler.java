package com.lhuang.blog.chatroom.api.handler.client;

import com.lhuang.blog.chatroom.api.protocol.packet.response.JoinGroupResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * @author LHuang
 * @since 2019/5/23
 */
@Slf4j
public class JoinGroupResponseHandler extends SimpleChannelInboundHandler<JoinGroupResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, JoinGroupResponsePacket joinGroupResponsePacket) throws Exception {
        if (joinGroupResponsePacket.getSuccess()){
            log.info("加入群组【"+joinGroupResponsePacket.getGroupID()+"】成功");
            return;
        }

        log.info("加入群组失败："+joinGroupResponsePacket.getReason());

    }
}
