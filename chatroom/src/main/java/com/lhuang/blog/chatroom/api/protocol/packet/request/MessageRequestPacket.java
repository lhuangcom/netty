package com.lhuang.blog.chatroom.api.protocol.packet.request;

import com.lhuang.blog.chatroom.api.protocol.packet.Packet;
import lombok.Data;

import static com.lhuang.blog.chatroom.api.command.Command.MESSAGE_REQUEST;

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
