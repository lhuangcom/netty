package com.lhuang.netty.im.common.api.protocol.packet.request;

import com.lhuang.netty.im.common.api.protocol.packet.Packet;
import lombok.Data;

import java.util.List;

import static com.lhuang.netty.im.common.api.command.Command.CREATE_GROUP_REQUEST;


/**
 * @author LHuang
 * @since 2019/5/20
 */
@Data
public class CreateGroupRequestPacket extends Packet {

    private List<String> userIDs;

    @Override
    public Byte getCommand() {
        return CREATE_GROUP_REQUEST;
    }
}
