package com.lhuang.blog.chatroom.api.protocol.packet.request;

import com.lhuang.blog.chatroom.api.protocol.packet.Packet;
import lombok.Data;

import static com.lhuang.blog.chatroom.api.command.Command.JOIN_GROUP_REQUEST;

/**
 * @author LHuang
 * @since 2019/5/23
 */
@Data
public class JoinGroupRequestPacket extends Packet {

    private String groupID;

    @Override
    public Byte getCommand() {
        return JOIN_GROUP_REQUEST;
    }
}
