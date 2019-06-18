package com.lhuang.netty.im.server.controller;

import com.lhuang.netty.im.common.api.pojo.ServerInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LHuang
 * @since 2019/6/10
 */
@RestController
public class RouteController {

    @Value("${im.server.port}")
    private int port;

    @RequestMapping("/serverInfo")
    public ServerInfo getServerInfo(){
        ServerInfo serverInfo = new ServerInfo();
        serverInfo.setPort(port);
        serverInfo.setIp("127.0.0.1");
        return serverInfo;
    }



}
