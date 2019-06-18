package com.lhuang.netty.im.common.api.protocol.packet.response;

import com.lhuang.netty.im.common.api.protocol.packet.Packet;
import lombok.Data;

import static com.lhuang.netty.im.common.api.command.Command.JOIN_GROUP_RESPOMSE;


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
