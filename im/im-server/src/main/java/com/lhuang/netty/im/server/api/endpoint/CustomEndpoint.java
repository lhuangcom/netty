package com.lhuang.netty.im.server.api.endpoint;

import com.lhuang.netty.im.common.api.util.SessionUtil;
import io.netty.channel.Channel;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 2.0版本无法使用继承AbstractEndpoint方法来定义endpoint
 * @author LHuang
 * @since 2019/6/5
 */

@Endpoint(id = "client-list")
@Configuration
public class CustomEndpoint{

    @ReadOperation
    public Map<String, Channel> endpoint() {
        return SessionUtil.userIdChannelMap;
    }

}
