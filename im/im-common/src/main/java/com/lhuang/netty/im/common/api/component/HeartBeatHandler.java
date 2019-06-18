package com.lhuang.netty.im.common.api.component;

import io.netty.channel.ChannelHandlerContext;

/**
 * @author LHuang
 * @since 2019/6/6
 */
public interface HeartBeatHandler {

    public void process(ChannelHandlerContext channelHandlerContext);
}
