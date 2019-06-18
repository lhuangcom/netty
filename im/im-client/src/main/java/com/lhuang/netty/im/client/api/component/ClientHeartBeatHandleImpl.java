package com.lhuang.netty.im.client.api.component;

import com.lhuang.netty.im.common.api.component.HeartBeatHandler;
import com.lhuang.netty.im.common.api.protocol.packet.request.HeartBeatRequestPacket;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author LHuang
 * @since 2019/6/6
 */
@Service
public class ClientHeartBeatHandleImpl implements HeartBeatHandler {

    @Override
    public void process(ChannelHandlerContext channelHandlerContext) {
        if (channelHandlerContext.channel().isActive()){
            channelHandlerContext.channel()
                .writeAndFlush(new HeartBeatRequestPacket())
                //ChannelFutureListener.CLOSE 默认的异步结果失败触发的回调线程
                .addListener(ChannelFutureListener.CLOSE_ON_FAILURE);
        }


    }
}
