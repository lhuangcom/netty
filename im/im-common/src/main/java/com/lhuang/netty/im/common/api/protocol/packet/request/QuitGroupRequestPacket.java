package com.lhuang.netty.im.common.api.protocol.packet.request;

import com.lhuang.netty.im.common.api.protocol.packet.Packet;
import lombok.Data;

import static com.lhuang.netty.im.common.api.command.Command.QUIT_GROUP_REQUEST;

/**
 * @author LHuang
 * @since 2019/5/21
 */
@Data
public class QuitGroupRequestPacket extends Packet {

    private String groupID;

    @Override
    public Byte getCommand() {
        return QUIT_GROUP_REQUEST;
    }
}
