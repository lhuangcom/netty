package com.lhuang.netty.im.client;


import com.lhuang.netty.im.client.api.init.IMClientChannelInitializer;
import com.lhuang.netty.im.client.api.thread.StartConsoleJob;
import com.lhuang.netty.im.client.api.thread.ThreadPool;
import com.lhuang.netty.im.client.controller.IMServerRemote;
import com.lhuang.netty.im.common.api.pojo.ServerInfo;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * 实现 CommandLineRunner 接口,并在类上面添加@Component,springboot会自动扫描这些注解，即可完成初始化，
 * 注意：这个初始化是在springboot加载完了所有bean之后执行的初始化方法
 * @author LHuang
 * @since 2019/5/9
 */
@Slf4j
@Component
public class NettyClient implements CommandLineRunner {

    private  int MAX_RETRY = 5;

    @Autowired
    private IMServerRemote imServerRemote;

    public void start(){
        Bootstrap bootstrap = new Bootstrap();
        NioEventLoopGroup output = new NioEventLoopGroup();
        bootstrap.group(output)
                .channel(NioSocketChannel.class)
                .handler(new IMClientChannelInitializer());


        ServerInfo serverInfo = imServerRemote.getServerInfo();
        int count = 0;
        while (count++ < 10){
            System.out.println(imServerRemote.getServerInfo());

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        log.info(serverInfo.toString());
        bind(bootstrap,serverInfo.getIp(),serverInfo.getPort(),MAX_RETRY);
        //bind(bootstrap,"127.0.0.1",8000,MAX_RETRY);

    }

    private void bind(Bootstrap bootstrap,String host, int hostPort,int retry){

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

    private  void  startConsoleThread(Channel channel){
        ThreadPool.getInstance().execute(new StartConsoleJob(channel));
    }

    @Override
    public void run(String... args) throws Exception {
        start();
    }
}
