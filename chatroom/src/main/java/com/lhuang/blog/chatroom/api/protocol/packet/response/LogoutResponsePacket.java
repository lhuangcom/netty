package com.lhuang.blog.chatroom.api.protocol.packet.response;

import com.lhuang.blog.chatroom.api.protocol.packet.Packet;
import lombok.Data;

import static com.lhuang.blog.chatroom.api.command.Command.LOGOUT_RESPONSE;

/**
 * @author LHuang
 * @since 2019/5/21
 */

@Data
public class LogoutResponsePacket extends Packet {

    private Boolean successs;

    @Override
    public Byte getCommand() {
        return LOGOUT_RESPONSE;
    }
}
