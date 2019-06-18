package com.lhuang.netty.im.client.api.handler;

import com.lhuang.netty.im.common.api.protocol.packet.response.CreateGroupResponsePacket;
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
public class CreateGroupResponseHandler extends SimpleChannelInboundHandler<CreateGroupResponsePacket> {


    public static final CreateGroupResponseHandler INSTANCE = new CreateGroupResponseHandler();

    private CreateGroupResponseHandler() {

    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, CreateGroupResponsePacket createGroupResponsePacket) throws Exception {

        log.info("群创建成功，id 为[" + createGroupResponsePacket.getGroupId() + "], ");
        log.info("群里面有：" + createGroupResponsePacket.getUserNameList());
    }
}
