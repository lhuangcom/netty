package com.lhuang.blog.chatroom.api.protocol.packet.response;

import com.lhuang.blog.chatroom.api.protocol.packet.Packet;
import lombok.Data;

import static com.lhuang.blog.chatroom.api.command.Command.JOIN_GROUP_RESPOMSE;

/**
 * @author LHuang
 * @since 2019/5/23
 */
@Data
public class JoinGroupResponsePacket extends Packet {

    private Boolean success;

    private String groupID;

    private String reason;

    @Override
    public Byte getCommand() {
        return JOIN_GROUP_RESPOMSE;
    }
}
