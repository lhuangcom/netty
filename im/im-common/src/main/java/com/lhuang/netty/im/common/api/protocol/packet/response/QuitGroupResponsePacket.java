package com.lhuang.netty.im.common.api.protocol.packet.response;

import com.lhuang.netty.im.common.api.protocol.packet.Packet;
import lombok.Data;

import static com.lhuang.netty.im.common.api.command.Command.QUIT_GROUP_RESPONSE;


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
