package com.lhuang.netty.im.common.api.protocol.packet.response;

import com.lhuang.netty.im.common.api.protocol.packet.Packet;
import lombok.Data;

import java.util.List;

import static com.lhuang.netty.im.common.api.command.Command.CREATE_GROUP_RESPONSE;


/**
 * @author LHuang
 * @since 2019/5/20
 */
@Data
public class CreateGroupResponsePacket extends Packet {

    private boolean success;

    private String groupId;

    private List<String> userNameList;

    @Override
    public Byte getCommand() {

        return CREATE_GROUP_RESPONSE;
    }
}
