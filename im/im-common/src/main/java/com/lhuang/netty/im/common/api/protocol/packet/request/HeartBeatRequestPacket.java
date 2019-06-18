package com.lhuang.netty.im.common.api.protocol.packet.request;


import com.lhuang.netty.im.common.api.protocol.packet.Packet;

import static com.lhuang.netty.im.common.api.command.Command.HEARTBEAT_REQUEST;

/**
 * @author LHuang
 * @since 2019/5/24
 */
public class HeartBeatRequestPacket extends Packet {
    @Override
    public Byte getCommand() {
        return HEARTBEAT_REQUEST;
    }
}
