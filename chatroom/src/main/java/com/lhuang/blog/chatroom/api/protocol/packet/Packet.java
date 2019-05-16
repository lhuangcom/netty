package com.lhuang.blog.chatroom.api.protocol.packet;

import lombok.Data;

@Data
public abstract class Packet {

    public Byte version = 1;

    public abstract Byte getCommand();
}
