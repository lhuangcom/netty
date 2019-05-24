package com.lhuang.blog.chatroom.api.handler.server;

import com.lhuang.blog.chatroom.api.pojo.Session;
import com.lhuang.blog.chatroom.api.util.IDUtil;
import com.lhuang.blog.chatroom.api.util.LoginUtil;
import com.lhuang.blog.chatroom.api.protocol.packet.request.LoginRequestPacket;
import com.lhuang.blog.chatroom.api.protocol.packet.response.LoginResponsePacket;
import com.lhuang.blog.chatroom.api.util.SessionUtil;
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
public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {

    public static final LoginRequestHandler INSTANCE = new LoginRequestHandler();

    private LoginRequestHandler() {
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {

        SessionUtil.unBindSession(ctx.channel());
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, LoginRequestPacket loginRequestPacket) {

        LoginResponsePacket loginResponsePacket = new LoginResponsePacket();

        loginResponsePacket.setVersion(loginRequestPacket.getVersion());

        log.info("处理客户端登录");


        // 登录校验
        if (valid(loginRequestPacket)) {

            // 校验成功
            LoginUtil.markLogin(channelHandlerContext.channel());

            String userID = IDUtil.randomID();

            Session session = new Session();
            session.setUserID(userID);
            session.setUserName(loginRequestPacket.getUsername());

            SessionUtil.bindSession(session,channelHandlerContext.channel());

            loginResponsePacket.setSuccess(true);
            loginResponsePacket.setUserID(userID);
            loginResponsePacket.setUsername(loginRequestPacket.getUsername());

            log.info("客户端登录成功");


        } else {

            // 校验失败
            loginResponsePacket.setSuccess(false);
            loginResponsePacket.setReason("账号密码校验失败");

            log.info(new Date() +"登录失败");
        }

        channelHandlerContext.channel().writeAndFlush(loginResponsePacket);
    }


    private boolean valid(LoginRequestPacket loginRequestPacket) {
        return true;
    }



}
