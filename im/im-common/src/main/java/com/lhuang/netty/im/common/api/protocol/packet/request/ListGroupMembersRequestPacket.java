package com.lhuang.netty.im.common.api.protocol.packet.request;

import com.lhuang.netty.im.common.api.protocol.packet.Packet;
import lombok.Data;

import static com.lhuang.netty.im.common.api.command.Command.LIST_GROUP_MEMBERS_REQUEST;


/**
 * @author LHuang
 * @since 2019/5/23
 */
@Data
public class ListGroupMembersRequestPacket extends Packet {

    private String groupID;

    @Override
    public Byte getCommand() {
        return LIST_GROUP_MEMBERS_REQUEST;
    }
}
