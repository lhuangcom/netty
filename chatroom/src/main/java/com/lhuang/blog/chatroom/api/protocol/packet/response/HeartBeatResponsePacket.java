package com.lhuang.blog.chatroom.api.protocol.packet.response;


import com.lhuang.blog.chatroom.api.protocol.packet.Packet;

import static com.lhuang.blog.chatroom.api.command.Command.HEARTBEAT_RESPONSE;

/**
 * @author LHuang
 * @since 2019/5/24
 */
public class HeartBeatResponsePacket extends Packet {
    @Override
    public Byte getCommand() {
        return HEARTBEAT_RESPONSE;
    }
}
