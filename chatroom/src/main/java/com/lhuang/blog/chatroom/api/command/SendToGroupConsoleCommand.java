package com.lhuang.blog.chatroom.api.command;

import com.lhuang.blog.chatroom.api.protocol.packet.request.GroupMessageRequestPacket;
import com.lhuang.blog.chatroom.api.protocol.packet.request.JoinGroupRequestPacket;
import io.netty.channel.Channel;
import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;

/**
 * @author LHuang
 * @since 2019/5/24
 */
@Slf4j
public class SendToGroupConsoleCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner scanner, Channel channel) {

        System.out.println("输入群组的id 和 发送消息的内容，中间以空格区分");

        String groupId = scanner.next();

        String message = scanner.next();

        GroupMessageRequestPacket groupMessageRequestPacket = new GroupMessageRequestPacket();

        groupMessageRequestPacket.setGroupID(groupId);

        groupMessageRequestPacket.setMessage(message);

        channel.writeAndFlush(groupMessageRequestPacket);
    }
}
