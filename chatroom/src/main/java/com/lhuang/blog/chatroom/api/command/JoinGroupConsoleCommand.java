package com.lhuang.blog.chatroom.api.command;

import com.lhuang.blog.chatroom.api.protocol.packet.request.JoinGroupRequestPacket;
import io.netty.channel.Channel;
import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;

/**
 * @author LHuang
 * @since 2019/5/23
 */
@Slf4j
public class JoinGroupConsoleCommand implements  ConsoleCommand {
    @Override
    public void exec(Scanner scanner, Channel channel) {

       log.info("输入 groupId，加入群聊：");

        String groupId = scanner.next();

        JoinGroupRequestPacket joinGroupRequestPacket = new JoinGroupRequestPacket();

        joinGroupRequestPacket.setGroupID(groupId);

        channel.writeAndFlush(joinGroupRequestPacket);
    }
}
