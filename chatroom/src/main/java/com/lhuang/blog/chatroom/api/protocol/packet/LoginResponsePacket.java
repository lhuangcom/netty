package com.lhuang.blog.chatroom.api.protocol.packet;

import lombok.Data;

import static com.lhuang.blog.chatroom.api.protocol.Command.LOGIN_RESPONSE;

/**
 * @author LHuang
 * @since 2019/5/14
 */
@Data
public class LoginResponsePacket extends Packet{
    private boolean success;

    private String reason;

    @Override
    public Byte getCommand() {
        return LOGIN_RESPONSE;
    }
}
