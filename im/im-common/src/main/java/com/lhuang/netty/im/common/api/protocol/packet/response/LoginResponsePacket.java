package com.lhuang.netty.im.common.api.protocol.packet.response;

import com.lhuang.netty.im.common.api.protocol.packet.Packet;
import lombok.Data;

import static com.lhuang.netty.im.common.api.command.Command.LOGIN_RESPONSE;


/**
 * @author LHuang
 * @since 2019/5/14
 */
@Data
public class LoginResponsePacket extends Packet {
    private boolean success;

    private String reason;

    private String username;

    private String userID;

    @Override
    public Byte getCommand() {
        return LOGIN_RESPONSE;
    }
}
