package com.lhuang.blog.chatroom.api.handler;

import com.lhuang.blog.chatroom.api.factory.PacketCode;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @author LHuang
 * @since 2019/5/15
 */
public class PacketDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list)  {

        list.add(PacketCode.getInstance().decode(byteBuf));

    }
}
