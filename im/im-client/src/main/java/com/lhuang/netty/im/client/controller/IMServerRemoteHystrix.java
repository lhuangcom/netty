package com.lhuang.netty.im.client.controller;

import com.lhuang.netty.im.common.api.pojo.ServerInfo;
import org.springframework.stereotype.Component;

@Component
public class IMServerRemoteHystrix  implements IMServerRemote{

    @Override
    public ServerInfo getServerInfo() {
        return null;
    }
}
