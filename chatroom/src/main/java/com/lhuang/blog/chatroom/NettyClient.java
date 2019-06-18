package com.lhuang.blog.chatroom;

import com.lhuang.blog.chatroom.api.command.LoginConsoleCommand;
import com.lhuang.blog.chatroom.api.factory.ConsoleCommandManager;
import com.lhuang.blog.chatroom.api.handler.*;
import com.lhuang.blog.chatroom.api.handler.client.*;
import com.lhuang.blog.chatroom.api.handler.server.IMIdleStateHandler;
import com.lhuang.blog.chatroom.api.util.LoginUtil;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * @author LHuang
 * @since 2019/5/9
 */
@Slf4j
public class NettyClient {

    private static int MAX_RETRY = 5;


    public static void main(String[] args){
        Bootstrap bootstrap = new Bootstrap();

        NioEventLoopGroup output = new NioEventLoopGroup();

        bootstrap.group(output)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel nioSocketChannel) {

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
                        nioSocketChannel.pipeline().addLast(HeartBeatTimerHandler.INSTANCE);
                        //nioSocketChannel.pipeline().addLast(new LifeCycleHandler());

                    }
                });


        bind(bootstrap,"127.0.0.1",8000,MAX_RETRY);


    }


    private static void bind(Bootstrap bootstrap,String host, int hostPort,int retry){

        bootstrap.connect(host,hostPort).addListener(future -> {
            if (future.isSuccess()){
              log.info("连接成功");
              Channel channel = ((ChannelFuture)future).channel();
              //开启消息输入客户端
              startConsoleThread(channel);
            }else if (retry == 0){
                log.error("可供重连次数已用完，连接失败");
            }
            else {
                //第几次重连
                int order = (MAX_RETRY - retry )+1;
                int delay = 1 << order;
                log.error("第"+order+"次连接失败,+"+delay+"秒后尝试重连");
                bootstrap.config().group().schedule(()->
                    bind(bootstrap,host,hostPort,retry-1)
                ,delay,TimeUnit.SECONDS);

            }
        }).channel();

    }

    private static  void  startConsoleThread(Channel channel){

        ConsoleCommandManager consoleCommandManager = new ConsoleCommandManager();
        LoginConsoleCommand loginConsoleCommand = new LoginConsoleCommand();


        Scanner scanner = new Scanner(System.in);

        new Thread(() -> {
            while (!Thread.interrupted()) {
                if (!LoginUtil.hasLogin(channel) ) {
                    loginConsoleCommand.exec(scanner,channel);
                } else {
                    consoleCommandManager.exec(scanner,channel);
                }
            }
        }).start();
    }

}
