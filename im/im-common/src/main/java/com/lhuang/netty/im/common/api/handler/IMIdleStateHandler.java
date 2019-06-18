package com.lhuang.netty.im.common.api.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

/**
 * @author LHuang
 * @since 2019/5/24
 *
 * 用于触发HeartBeatTimerHandler中的userEventTriggered()的方法
 */
public class IMIdleStateHandler extends IdleStateHandler {
    private static final int WRITE_IDLE_TIME = 5;

    public IMIdleStateHandler() {
        super(0, WRITE_IDLE_TIME, 0, TimeUnit.SECONDS);
    }

    @Override
    protected void channelIdle(ChannelHandlerContext ctx, IdleStateEvent evt) throws Exception {
        //传递下去才能触发相应的事件
        super.channelIdle(ctx,evt);
    }
}
