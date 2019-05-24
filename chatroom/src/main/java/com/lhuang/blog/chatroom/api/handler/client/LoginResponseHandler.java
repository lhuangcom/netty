package com.lhuang.blog.chatroom.api.handler.client;

import com.lhuang.blog.chatroom.api.util.LoginUtil;
import com.lhuang.blog.chatroom.api.protocol.packet.response.LoginResponsePacket;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

/**
 * @author LHuang
 * @since 2019/5/15
 */
@Slf4j
@ChannelHandler.Sharable
public class LoginResponseHandler extends SimpleChannelInboundHandler<LoginResponsePacket> {

    public static final LoginResponseHandler INSTANCE = new LoginResponseHandler();

    private LoginResponseHandler() {
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx)  {
        log.info(new Date()+": 客户端开始登录");


        //创建登录对象

       /* LoginRequestPacket loginRequestPacket =  new LoginRequestPacket();
        loginRequestPacket.setUserId(UUID.randomUUID().toString());
        loginRequestPacket.setUsername("lhuang");
        loginRequestPacket.setPassword("123456");*/

       // ctx.channel().writeAndFlush(loginRequestPacket);

    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, LoginResponsePacket loginResponsePacket) {
        String userId = loginResponsePacket.getUserID();
        String userName = loginResponsePacket.getUsername();

        if (loginResponsePacket.isSuccess()) {
            System.out.println("[" + userName + "]登录成功，userId 为: " + userId);
           LoginUtil.markLogin(channelHandlerContext.channel());
        } else {
            System.out.println("[" + userName + "]登录失败，原因：" + loginResponsePacket.getReason());
        }
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
       log.info("客户端连接被关闭!");
    }
}
