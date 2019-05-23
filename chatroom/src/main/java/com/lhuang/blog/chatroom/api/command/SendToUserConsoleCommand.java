package com.lhuang.blog.chatroom.api.command;

import com.lhuang.blog.chatroom.api.protocol.packet.request.MessageForwardPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @author LHuang
 * @since 2019/5/20
 */
public class SendToUserConsoleCommand implements ConsoleCommand{
    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.println("输入用户的id 和 发送消息的内容，中间以空格区分");
        String toUserId = scanner.next();
        String message = scanner.next();
        channel.writeAndFlush(new MessageForwardPacket(toUserId, message));
    }
}
