package com.lhuang.blog.chatroom.api.protocol.packet.response;

import com.lhuang.blog.chatroom.api.protocol.packet.Packet;
import lombok.Data;

import static com.lhuang.blog.chatroom.api.command.Command.LOGOUT_RESPONSE;
import static com.lhuang.blog.chatroom.api.command.Command.QUIT_GROUP_RESPONSE;

/**
 * @author LHuang
 * @since 2019/5/21
 */

@Data
public class QuitGroupResponsePacket extends Packet {

    private Boolean success;

    private String reason;

    private String groupID;

    @Override
    public Byte getCommand() {
        return QUIT_GROUP_RESPONSE;
    }
}
