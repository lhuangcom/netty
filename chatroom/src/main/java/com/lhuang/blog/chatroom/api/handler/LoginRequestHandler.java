package com.lhuang.blog.chatroom.api.handler;

import com.lhuang.blog.chatroom.api.protocol.packet.LoginRequestPacket;
import com.lhuang.blog.chatroom.api.protocol.packet.LoginResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * @author LHuang
 * @since 2019/5/15
 */
@Slf4j
public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, LoginRequestPacket loginRequestPacket) {

        LoginResponsePacket loginResponsePacket = new LoginResponsePacket();

        loginResponsePacket.setVersion(loginRequestPacket.getVersion());

        log.info("处理客户端登录");


        // 登录校验
        if (valid(loginRequestPacket)) {

            loginResponsePacket.setSuccess(true);
            log.info("客户端登录成功");
            // 校验成功
        } else {
            // 校验失败
            loginResponsePacket.setSuccess(false);
            loginResponsePacket.setReason("账号密码校验失败");
        }

        channelHandlerContext.channel().writeAndFlush(loginResponsePacket);
    }


    private boolean valid(LoginRequestPacket loginRequestPacket) {
        return true;
    }

}
