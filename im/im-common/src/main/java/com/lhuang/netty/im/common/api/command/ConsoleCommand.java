package com.lhuang.netty.im.common.api.command;

import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * 控制台命令执行器
 * @author LHuang
 * @since 2019/5/20
 */
public interface ConsoleCommand  {

    void exec(Scanner scanner, Channel channel);
}
