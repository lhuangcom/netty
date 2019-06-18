package com.lhuang.netty.im.client.api.command;

import com.lhuang.netty.im.common.api.command.ConsoleCommand;
import com.lhuang.netty.im.common.api.protocol.packet.request.LogoutRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @author LHuang
 * @since 2019/5/21
 */
public class LogoutConsoleCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner scanner, Channel channel) {

        LogoutRequestPacket logoutRequestPacket = new LogoutRequestPacket();
        channel.writeAndFlush(logoutRequestPacket);

    }
}
