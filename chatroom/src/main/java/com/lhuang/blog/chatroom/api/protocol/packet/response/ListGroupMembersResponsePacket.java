package com.lhuang.blog.chatroom.api.protocol.packet.response;

import com.lhuang.blog.chatroom.api.pojo.Session;
import com.lhuang.blog.chatroom.api.protocol.packet.Packet;
import lombok.Data;

import java.util.List;

import static com.lhuang.blog.chatroom.api.command.Command.LIST_GROUP_MEMBERS_REQUEST;
import static com.lhuang.blog.chatroom.api.command.Command.LIST_GROUP_MEMBERS_RESPONSE;

/**
 * @author LHuang
 * @since 2019/5/23
 */
@Data
public class ListGroupMembersResponsePacket extends Packet {

    private boolean success;

    private String groupId;

    private List<Session> sessionList;

    @Override
    public Byte getCommand() {
        return LIST_GROUP_MEMBERS_RESPONSE;
    }
}
