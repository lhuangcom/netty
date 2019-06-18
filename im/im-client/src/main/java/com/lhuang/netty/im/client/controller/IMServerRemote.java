package com.lhuang.netty.im.client.controller;

import com.lhuang.netty.im.common.api.pojo.ServerInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "im-server",fallback = IMServerRemoteHystrix.class)
public interface IMServerRemote {

    @RequestMapping("/serverInfo")
    public ServerInfo getServerInfo();


}
