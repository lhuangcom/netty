package com.lhuang.netty.im.server.api.init;

import com.lhuang.netty.im.common.api.handler.IMIdleStateHandler;
import com.lhuang.netty.im.common.api.handler.PacketCodecHandler;
import com.lhuang.netty.im.common.api.handler.Spliter;
import com.lhuang.netty.im.server.api.handler.AuthHandler;
import com.lhuang.netty.im.server.api.handler.HeartBeatRequestHandler;
import com.lhuang.netty.im.server.api.handler.IMHandler;
import com.lhuang.netty.im.server.api.handler.LoginRequestHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @author LHuang
 * @since 2019/6/5
 */
public class IMServerChannelInitializer extends ChannelInitializer<NioSocketChannel> {

    @Override
    protected void initChannel(NioSocketChannel nioSocketChannel) throws Exception {
        nioSocketChannel.pipeline().addLast(new IMIdleStateHandler());
        nioSocketChannel.pipeline().addLast(new Spliter());
        nioSocketChannel.pipeline().addLast(PacketCodecHandler.INSTANCE);
        nioSocketChannel.pipeline().addLast(LoginRequestHandler.INSTANCE);
        nioSocketChannel.pipeline().addLast(HeartBeatRequestHandler.INSTANCE);
        nioSocketChannel.pipeline().addLast(AuthHandler.INSTANCE);
        nioSocketChannel.pipeline().addLast(IMHandler.INSTANCE);
    }
}
