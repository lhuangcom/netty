package com.lhuang.netty.im.client.api.component;

import com.lhuang.netty.im.client.NettyClient;
import com.lhuang.netty.im.common.api.component.HeartBeatHandler;
import com.lhuang.netty.im.common.api.util.LoginUtil;
import io.netty.channel.ChannelHandlerContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author LHuang
 * @since 2019/6/6
 */
@Service
public class ReConnectHeartBeatHandleImpl implements HeartBeatHandler {

    @Autowired
    private NettyClient nettyClient;

    @Override
    public void process(ChannelHandlerContext channelHandlerContext) {

        //处理重连后仍残存旧的输入线程
        killMainJob();

        if (!channelHandlerContext.channel().isActive()){
           //由于重连后客户端注册信息仍未删除，需要清除该部分信息，避免无法识别命令，无法注册服务器
            if (LoginUtil.hasLogin(channelHandlerContext.channel())){
                //处理未登录重启
                LoginUtil.markLoginout(channelHandlerContext.channel());

            }

            nettyClient.start();
        }

    }

    public void killMainJob(){
        ThreadGroup threadGroup = Thread.currentThread().getThreadGroup();
        int activeCount = threadGroup.activeCount();
        Thread[] threads = new Thread[activeCount];
        threadGroup.enumerate(threads);
        for (Thread thread : threads){
            if ("mainJob".equals(thread.getName())){
                //interrupt()并不能完全终止一个正在运行的线程；
                thread.interrupt();
            }
        }

    }
}
