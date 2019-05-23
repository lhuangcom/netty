package com.lhuang.blog.chatroom.api.command;

import com.lhuang.blog.chatroom.api.protocol.packet.request.CreateGroupRequestPacket;
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
public class CreateGroupConsoleCommand implements ConsoleCommand {

    private static final String USER_ID_SPLITER = ",";

    @Override
    public void exec(Scanner scanner, Channel channel) {

        log.info("【拉人群聊】输入 userId 列表，userId 之间英文逗号隔开：");

        String userIDs = scanner.next();

        CreateGroupRequestPacket createGroupRequestPacket = new CreateGroupRequestPacket();

        createGroupRequestPacket.setUserIDs(new ArrayList<String>(Arrays.asList(userIDs.split(USER_ID_SPLITER))));

        channel.writeAndFlush(createGroupRequestPacket);

    }
}
