package com.lhuang.netty.im.client.api.thread;

import com.lhuang.netty.im.client.api.command.LoginConsoleCommand;
import com.lhuang.netty.im.client.api.factory.ConsoleCommandManager;
import com.lhuang.netty.im.common.api.util.LoginUtil;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @author LHuang
 * @since 2019/6/10
 */
public class StartConsoleJob implements Runnable {


    private Channel channel;

    public StartConsoleJob(Channel channel){
        this.channel = channel;
    }

    @Override
    public void run() {
        //用于重启后中断该线程的标志
        Thread.currentThread().setName("mainJob");

        ConsoleCommandManager consoleCommandManager = new ConsoleCommandManager();
        LoginConsoleCommand loginConsoleCommand = new LoginConsoleCommand();
        Scanner scanner = new Scanner(System.in);
        while (channel.isActive() && !Thread.currentThread().isInterrupted()) {
            if (!LoginUtil.hasLogin(channel) ) {
                loginConsoleCommand.exec(scanner,channel);
            } else {
                consoleCommandManager.exec(scanner,channel);
            }
        }

    }
}
