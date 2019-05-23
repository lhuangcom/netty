package com.lhuang.blog.chatroom.api.protocol.packet.request;

import com.lhuang.blog.chatroom.api.protocol.packet.Packet;
import lombok.Data;

import static com.lhuang.blog.chatroom.api.command.Command.LOGIN_REQUEST;

@Data
public class LoginRequestPacket extends Packet {

    private String username;

    private String password;

    @Override
    public Byte getCommand() {
        return LOGIN_REQUEST;
    }
}
