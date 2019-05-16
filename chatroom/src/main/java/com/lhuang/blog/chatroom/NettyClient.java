package com.lhuang.blog.chatroom;

import com.lhuang.blog.chatroom.api.LoginUtil;
import com.lhuang.blog.chatroom.api.handler.*;
import com.lhuang.blog.chatroom.api.protocol.packet.MessageRequestPacket;
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
                        nioSocketChannel.pipeline().addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE,7,4));
                        nioSocketChannel.pipeline().addLast(new PacketDecoder());
                        nioSocketChannel.pipeline().addLast(new LoginResponseHandler());
                        nioSocketChannel.pipeline().addLast(new MessageResponseHandler());
                        nioSocketChannel.pipeline().addLast(new PacketEncoder());
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
        new Thread(()->{
            while (!Thread.interrupted()){
                if (LoginUtil.hasLogin(channel)){
                    log.info("输入消息发送至服务端:");
                    Scanner scanner = new Scanner(System.in);

                    String line = scanner.nextLine();

                    MessageRequestPacket messageRequestPacket = new MessageRequestPacket();

                    messageRequestPacket.setMessage(line);

                    channel.writeAndFlush(messageRequestPacket);

                }

            }
        }).start();

    }



}
