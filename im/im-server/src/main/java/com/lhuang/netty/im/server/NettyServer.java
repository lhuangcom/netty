package com.lhuang.netty.im.server;


import com.lhuang.netty.im.server.api.init.IMServerChannelInitializer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.concurrent.Future;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.net.InetSocketAddress;


/**
 * @author LHuang
 * @since 2019/5/9
 */
@Slf4j
@Component
public class NettyServer {


    NioEventLoopGroup boss = new NioEventLoopGroup();
    NioEventLoopGroup worker = new NioEventLoopGroup();

    @Value("${im.server.port}")
    private int port;


    @PostConstruct
    public void start() throws InterruptedException {
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(boss,worker)
                .channel(NioServerSocketChannel.class)
                .childHandler(new IMServerChannelInitializer())
                //绑定本机的ip地址和端口号
                .localAddress(new InetSocketAddress("127.0.0.1",port))
                //保持长连接
                .childOption(ChannelOption.SO_KEEPALIVE,true);
        bind(serverBootstrap);
    }

    @PreDestroy
    public void destroy(){
        boss.shutdownGracefully().syncUninterruptibly();
        worker.shutdownGracefully().syncUninterruptibly();
        log.info("服务器关闭成功");
    }

    private static void bind(ServerBootstrap serverBootstrap) throws InterruptedException {
        serverBootstrap.bind().sync().addListener((Future<? super Void> future) -> {
                if (future.isSuccess()){
                    log.info("主机端口绑定成功");
                }
                else {
                    log.info("主机端口绑定失败");
                }

        });

    }

}
