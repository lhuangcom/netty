package com.lhuang.blog.chatroom.api.handler;

import com.lhuang.blog.chatroom.api.LoginUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

/**
 * @author LHuang
 * @since 2019/5/17
 */
@Slf4j
public class AuthHandler extends ChannelInboundHandlerAdapter {


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        if (!LoginUtil.hasLogin(ctx.channel())){
            ctx.channel().close();
            log.info("没有登录，关闭链接");
            return;
        }
        ctx.pipeline().remove(this);
        super.channelRead(ctx, msg);
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) {

        if (LoginUtil.hasLogin(ctx.channel())) {
            System.out.println("当前连接登录验证完毕，无需再次验证, AuthHandler 被移除");
        } else {
            System.out.println("无登录验证，强制关闭连接!");
        }

    }
}
