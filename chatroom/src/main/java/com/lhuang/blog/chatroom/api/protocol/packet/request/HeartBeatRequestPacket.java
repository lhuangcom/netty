package com.lhuang.blog.chatroom.api.protocol.packet.request;

import com.lhuang.blog.chatroom.api.protocol.packet.Packet;

import static com.lhuang.blog.chatroom.api.command.Command.HEARTBEAT_REQUEST;

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
