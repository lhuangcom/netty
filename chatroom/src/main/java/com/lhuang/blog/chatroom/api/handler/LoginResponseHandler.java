package com.lhuang.blog.chatroom.api.handler;

import com.lhuang.blog.chatroom.api.LoginUtil;
import com.lhuang.blog.chatroom.api.protocol.packet.LoginRequestPacket;
import com.lhuang.blog.chatroom.api.protocol.packet.LoginResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.UUID;

/**
 * @author LHuang
 * @since 2019/5/15
 */
@Slf4j
public class LoginResponseHandler extends SimpleChannelInboundHandler<LoginResponsePacket> {
    @Override
    public void channelActive(ChannelHandlerContext ctx)  {
        log.info(new Date()+": 客户端开始登录");


        //创建登录对象
        LoginRequestPacket loginRequestPacket =  new LoginRequestPacket();
        loginRequestPacket.setUserId(UUID.randomUUID().toString());
        loginRequestPacket.setUsername("lhuang");
        loginRequestPacket.setPassword("123456");

        ctx.channel().writeAndFlush(loginRequestPacket);

    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, LoginResponsePacket loginResponsePacket) {
        if (loginResponsePacket.isSuccess()){
            LoginUtil.markLogin(channelHandlerContext.channel());
            log.info(new Date() + ": 客户端登录成功");
        } else {
            log.info(new Date() + ": 客户端登录失败，原因：" + loginResponsePacket.getReason());
        }
    }
}
