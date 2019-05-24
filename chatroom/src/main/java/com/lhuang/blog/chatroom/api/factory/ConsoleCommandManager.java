package com.lhuang.blog.chatroom.api.factory;

import com.lhuang.blog.chatroom.api.command.*;
import com.lhuang.blog.chatroom.api.util.LoginUtil;
import io.netty.channel.Channel;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 管理控制台命令执行器
 * @author LHuang
 * @since 2019/5/20
 */
@Slf4j
public class ConsoleCommandManager implements ConsoleCommand {

    private Map<String,ConsoleCommand> consoleCommandMap;

    public ConsoleCommandManager(){
        consoleCommandMap = new HashMap<>();
        consoleCommandMap.put("createGroup",new CreateGroupConsoleCommand());
        consoleCommandMap.put("sendToUser",new SendToUserConsoleCommand());
        consoleCommandMap.put("logout",new LogoutConsoleCommand());
        consoleCommandMap.put("joinGroup", new JoinGroupConsoleCommand());
        consoleCommandMap.put("quitGroup",new QuitGroupConsoleCommand());
        consoleCommandMap.put("listGroupMembers",new ListGroupMembersConsoleCommand());
        consoleCommandMap.put("sendGroup",new SendToGroupConsoleCommand());

    }

    @Override
    public void exec(Scanner scanner, Channel channel) {

        String command = scanner.next();

        ConsoleCommand consoleCommand = consoleCommandMap.get(command);

        if (LoginUtil.hasLogin(channel) && consoleCommand != null){
            consoleCommand.exec(scanner,channel);
            return;
        }

        log.error("无法识别[" + command + "]指令，请重新输入!");

    }
}
