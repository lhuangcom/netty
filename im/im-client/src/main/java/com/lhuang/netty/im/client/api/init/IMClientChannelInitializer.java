package com.lhuang.netty.im.client.api.init;

import com.lhuang.netty.im.client.api.handler.*;
import com.lhuang.netty.im.common.api.handler.IMIdleStateHandler;
import com.lhuang.netty.im.common.api.handler.PacketCodecHandler;
import com.lhuang.netty.im.common.api.handler.Spliter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @author LHuang
 * @since 2019/6/5
 */
public class IMClientChannelInitializer extends ChannelInitializer<NioSocketChannel> {
    @Override
    protected void initChannel(NioSocketChannel nioSocketChannel) throws Exception {

        nioSocketChannel.pipeline().addLast(new IMIdleStateHandler());
        nioSocketChannel.pipeline().addLast(new Spliter());
        nioSocketChannel.pipeline().addLast(PacketCodecHandler.INSTANCE);
        nioSocketChannel.pipeline().addLast(LoginResponseHandler.INSTANCE);
        nioSocketChannel.pipeline().addLast(MessageResponseHandler.INSTANCE);
        nioSocketChannel.pipeline().addLast(CreateGroupResponseHandler.INSTANCE);
        nioSocketChannel.pipeline().addLast(LogoutResponseHandler.INSTANCE);
        nioSocketChannel.pipeline().addLast(JoinGroupResponseHandler.INSTANCE);
        nioSocketChannel.pipeline().addLast(QuitGroupResponseHandler.INSTANCE);
        nioSocketChannel.pipeline().addLast(ListGroupMembersResponseHandler.INSTANCE);
        nioSocketChannel.pipeline().addLast(GroupMessageResponseHandler.INSTANCE);

        // 心跳定时器
        nioSocketChannel.pipeline().addLast(new HeartBeatTimerHandler());
    }
}
