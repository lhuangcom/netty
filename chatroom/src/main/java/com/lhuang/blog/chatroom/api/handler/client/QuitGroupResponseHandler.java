package com.lhuang.blog.chatroom.api.handler.client;

import com.lhuang.blog.chatroom.api.protocol.packet.response.JoinGroupResponsePacket;
import com.lhuang.blog.chatroom.api.protocol.packet.response.QuitGroupResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * @author LHuang
 * @since 2019/5/23
 */
@Slf4j
public class QuitGroupResponseHandler extends SimpleChannelInboundHandler<QuitGroupResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, QuitGroupResponsePacket quitGroupResponsePacket) throws Exception {
        if (quitGroupResponsePacket.getSuccess()){
            log.info("退出群组【"+quitGroupResponsePacket.getGroupID()+"】成功");
            return;
        }

        log.info("退出群组失败："+quitGroupResponsePacket.getReason());

    }
}
