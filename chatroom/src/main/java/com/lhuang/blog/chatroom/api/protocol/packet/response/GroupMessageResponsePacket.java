package com.lhuang.blog.chatroom.api.protocol.packet.response;

import com.lhuang.blog.chatroom.api.pojo.Session;
import com.lhuang.blog.chatroom.api.protocol.packet.Packet;
import lombok.Data;

import static com.lhuang.blog.chatroom.api.command.Command.GROUP_MESSAGE_RESPONSE;


/**
 * @author LHuang
 * @since 2019/5/24
 */
@Data
public class GroupMessageResponsePacket extends Packet {

    private String message;

    private Session fromUser;

    private String fromGroupID;

    @Override
    public Byte getCommand() {

        return GROUP_MESSAGE_RESPONSE;

    }
}
