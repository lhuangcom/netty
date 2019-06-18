package com.lhuang.blog.chatroom;

import com.lhuang.blog.chatroom.api.handler.*;

import com.lhuang.blog.chatroom.api.handler.server.*;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.util.concurrent.Future;
import lombok.extern.slf4j.Slf4j;


/**
 * @author LHuang
 * @since 2019/5/9
 */
@Slf4j
public class NettyServer {

    public static void  main(String[] args){

        ServerBootstrap serverBootstrap = new ServerBootstrap();

        NioEventLoopGroup boss = new NioEventLoopGroup();
        NioEventLoopGroup worker = new NioEventLoopGroup();

        serverBootstrap.group(boss,worker)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel nioSocketChannel) {
                        nioSocketChannel.pipeline().addLast(new LifeCycleHandler());
                        nioSocketChannel.pipeline().addLast(new IMIdleStateHandler());
                        nioSocketChannel.pipeline().addLast(new Spliter());
                        nioSocketChannel.pipeline().addLast(PacketCodecHandler.INSTANCE);
                        nioSocketChannel.pipeline().addLast(LoginRequestHandler.INSTANCE);
                        nioSocketChannel.pipeline().addLast(HeartBeatRequestHandler.INSTANCE);
                        nioSocketChannel.pipeline().addLast(AuthHandler.INSTANCE);
                        nioSocketChannel.pipeline().addLast(IMHandler.INSTANCE);
                }});

        bind(serverBootstrap,8000);
    }

    private static void bind(ServerBootstrap serverBootstrap, int hostPort){
        serverBootstrap.bind(hostPort).addListener((Future<? super Void> future) -> {
                if (future.isSuccess()){
                    log.info(hostPort+" 端口绑定成功");
                }
                else {
                    log.info(hostPort+" 端口绑定失败");
                    bind(serverBootstrap,hostPort+1);
                }

        });

    }

}
