package com.lhuang.blog.chatroom.api.protocol.packet.response;

import com.lhuang.blog.chatroom.api.protocol.packet.Packet;
import lombok.Data;

import static com.lhuang.blog.chatroom.api.command.Command.MESSAGE_RESPONSE;

/**
 * @author LHuang
 * @since 2019/5/14
 */
@Data
public class MessageResponsePacket extends Packet {

    private String fromUserID;

    private String fromUserName;

    private String message;

    @Override
    public Byte getCommand() {
        return MESSAGE_RESPONSE;
    }
}
