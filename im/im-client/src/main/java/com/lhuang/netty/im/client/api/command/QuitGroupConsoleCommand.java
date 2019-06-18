package com.lhuang.netty.im.client.api.command;

import com.lhuang.netty.im.common.api.command.ConsoleCommand;
import com.lhuang.netty.im.common.api.protocol.packet.request.QuitGroupRequestPacket;
import io.netty.channel.Channel;
import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;

/**
 * @author LHuang
 * @since 2019/5/21
 */
@Slf4j
public class QuitGroupConsoleCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner scanner, Channel channel) {
        log.info("输入 groupId，退出群聊：");


        String groupID = scanner.next();

        QuitGroupRequestPacket quitGroupRequestPacket = new QuitGroupRequestPacket();
        quitGroupRequestPacket.setGroupID(groupID);
        channel.writeAndFlush(quitGroupRequestPacket);

    }
}
