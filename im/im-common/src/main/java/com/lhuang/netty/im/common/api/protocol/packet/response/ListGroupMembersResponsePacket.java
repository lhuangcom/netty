package com.lhuang.netty.im.common.api.protocol.packet.response;

import com.lhuang.netty.im.common.api.pojo.Session;
import com.lhuang.netty.im.common.api.protocol.packet.Packet;
import lombok.Data;

import java.util.List;

import static com.lhuang.netty.im.common.api.command.Command.LIST_GROUP_MEMBERS_RESPONSE;


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
