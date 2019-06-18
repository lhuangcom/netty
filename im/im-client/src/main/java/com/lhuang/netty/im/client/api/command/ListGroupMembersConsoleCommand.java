package com.lhuang.netty.im.client.api.command;

import com.lhuang.netty.im.common.api.command.ConsoleCommand;
import com.lhuang.netty.im.common.api.protocol.packet.request.ListGroupMembersRequestPacket;
import io.netty.channel.Channel;
import lombok.extern.slf4j.Slf4j;

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
