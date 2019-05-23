package com.lhuang.blog.chatroom.api.command;

import com.lhuang.blog.chatroom.api.protocol.packet.request.CreateGroupRequestPacket;
import com.lhuang.blog.chatroom.api.protocol.packet.request.ListGroupMembersRequestPacket;
import io.netty.channel.Channel;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * 创建分组命令
 * @author LHuang
 * @since 2019/5/20
 */
@Slf4j
public class ListGroupMembersConsoleCommand implements ConsoleCommand {

    @Override
    public void exec(Scanner scanner, Channel channel) {

        ListGroupMembersRequestPacket listGroupMembersRequestPacket = new ListGroupMembersRequestPacket();

        log.info("输入 groupId，获取群成员列表：");
        String groupID = scanner.next();

        listGroupMembersRequestPacket.setGroupID(groupID);
        channel.writeAndFlush(listGroupMembersRequestPacket);

    }
}
