package com.lhuang.blog.chatroom.api.protocol.packet;

import lombok.Data;

import static com.lhuang.blog.chatroom.api.protocol.Command.LOGIN_REQUEST;

@Data
public class LoginRequestPacket extends Packet{

    private String username;

    private String password;

    private String userId;

    @Override
    public Byte getCommand() {
        return LOGIN_REQUEST;
    }
}
