package com.lhuang.netty.im.client.api.handler;

import com.lhuang.netty.im.client.api.thread.ReConnectJob;
import com.lhuang.netty.im.client.api.thread.SendHeartBeatJob;
import com.lhuang.netty.im.client.api.thread.ThreadPool;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import lombok.extern.slf4j.Slf4j;

/**
 * @author LHuang
 * @since 2019/5/24
 */
@Slf4j
@ChannelHandler.Sharable
public class HeartBeatTimerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext channelHandlerContext) throws Exception {
        log.info("心跳检测激活");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        //服务器与客户端出现重新连接中断，进行重连操作；
        log.info("准备进行重连操作");
        ThreadPool.getInstance().execute(new ReConnectJob(ctx));
        super.channelInactive(ctx);
    }

    /**
     * 需要由IdleStateHandler的channelIdle的方法传递evt才会触发该方法
     *
     * 不应该在该方法中触发重连的逻辑，由于网络断开、Server宕机等原因，会直接调用channelInActive方法，从而销毁所有的Handler
     * 自然不会再触发该方法
     * @param ctx
     * @param evt
     * @throws Exception
     */
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent){
            IdleStateEvent idleStateEvent = (IdleStateEvent) evt;
            if(idleStateEvent.state() == IdleState.WRITER_IDLE){
                scheduleSendHeartBeat(ctx);
            }
        }
        //将事件传递下去，避免后续的handler无法触发到该类型事件；
        super.userEventTriggered(ctx, evt);
    }

    private void scheduleSendHeartBeat(ChannelHandlerContext channelHandlerContext) {

        ThreadPool.getInstance().execute(new SendHeartBeatJob(channelHandlerContext));

    }

}
