package com.lhuang.netty.im.common.api.protocol.packet;

import lombok.Data;

@Data
public abstract class Packet {

    public Byte version = 1;

    public abstract Byte getCommand();
}
