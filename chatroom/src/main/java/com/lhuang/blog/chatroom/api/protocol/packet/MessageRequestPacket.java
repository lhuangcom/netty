package com.lhuang.blog.chatroom.api.protocol.packet;

import lombok.Data;

import static com.lhuang.blog.chatroom.api.protocol.Command.MESSAGE_REQUEST;

/**
 * @author LHuang
 * @since 2019/5/14
 */
@Data
public class MessageRequestPacket extends Packet {

    private String message;

    @Override
    public Byte getCommand() {
        return MESSAGE_REQUEST;
    }
}
