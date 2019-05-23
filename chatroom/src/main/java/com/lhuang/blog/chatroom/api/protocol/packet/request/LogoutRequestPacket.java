package com.lhuang.blog.chatroom.api.protocol.packet.request;

import com.lhuang.blog.chatroom.api.protocol.packet.Packet;

import static com.lhuang.blog.chatroom.api.command.Command.LOGOUT_REQUEST;

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
