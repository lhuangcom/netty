package com.lhuang.netty.im.common.api.pojo;

import lombok.Data;
import lombok.ToString;

/**
 * @author LHuang
 * @since 2019/6/10
 */
@Data
@ToString
public class ServerInfo {

    private String ip;

    private int port;
}
