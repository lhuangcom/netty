package com.lhuang.netty.im.client.api.thread;

import com.lhuang.netty.im.client.api.component.ClientHeartBeatHandleImpl;
import com.lhuang.netty.im.client.api.component.ReConnectHeartBeatHandleImpl;
import com.lhuang.netty.im.common.api.component.HeartBeatHandler;
import com.lhuang.netty.im.common.api.factory.SpringBeanFactory;
import io.netty.channel.ChannelHandlerContext;

/**
 * @author LHuang
 * @since 2019/6/6
 */
public class SendHeartBeatJob implements Runnable {

    private ChannelHandlerContext channelHandlerContext;

    private HeartBeatHandler heartBeatHandler;


    public SendHeartBeatJob(ChannelHandlerContext channelHandlerContext){

        this.channelHandlerContext = channelHandlerContext;

        this.heartBeatHandler = SpringBeanFactory.getBean(ClientHeartBeatHandleImpl.class);

    }

    @Override
    public void run() {
        heartBeatHandler.process(channelHandlerContext);
    }
}
