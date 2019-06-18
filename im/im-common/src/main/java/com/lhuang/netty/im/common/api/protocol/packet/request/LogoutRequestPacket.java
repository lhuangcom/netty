package com.lhuang.netty.im.common.api.protocol.packet.request;


import com.lhuang.netty.im.common.api.protocol.packet.Packet;

import static com.lhuang.netty.im.common.api.command.Command.LOGOUT_REQUEST;

/**
 * @author LHuang
 * @since 2019/5/21
 */
public class LogoutRequestPacket extends Packet {
    @Override
    public Byte getCommand() {
        return LOGOUT_REQUEST;
    }
}
