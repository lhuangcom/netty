package com.lhuang.netty.im.common.api.protocol.packet.request;

import com.lhuang.netty.im.common.api.protocol.packet.Packet;
import lombok.AllArgsConstructor;
import lombok.Data;

import static com.lhuang.netty.im.common.api.command.Command.MESSAGE_FORWARD;


/**
 * @author LHuang
 * @since 2019/5/20
 */
@Data
@AllArgsConstructor
public class MessageForwardPacket extends Packet {

    private String toUserID;

    private String message;

    @Override
    public Byte getCommand() {
        return MESSAGE_FORWARD;
    }
}
