package com.lhuang.blog.chatroom.api.protocol.packet.request;

import com.lhuang.blog.chatroom.api.protocol.packet.Packet;
import lombok.Data;

import static com.lhuang.blog.chatroom.api.command.Command.GROUP_MESSAGE_REQUEST;


/**
 * @author LHuang
 * @since 2019/5/24
 */
@Data
public class GroupMessageRequestPacket extends Packet {

    private String groupID;

    private String message;

    @Override
    public Byte getCommand() {

        return GROUP_MESSAGE_REQUEST;

    }
}
