package com.lhuang.blog.chatroom.api.handler;

import com.lhuang.blog.chatroom.api.protocol.packet.PacketCode;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import lombok.extern.slf4j.Slf4j;

/**
 * @author LHuang
 * @since 2019/5/15
 */
@Slf4j
public class Spliter extends LengthFieldBasedFrameDecoder {

    private static final Integer LENGTHFIELDOFFSET = 7;

    private static final Integer LENGTHFIELDLENGTH = 4;

    public Spliter(){
        super(Integer.MAX_VALUE,LENGTHFIELDOFFSET,LENGTHFIELDLENGTH);
    }

    @Override
    protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {

        if (in.getInt(in.readInt()) != PacketCode.MAGIC_NUMBER){
            ctx.channel().close();
            log.info("通道关闭");

        }

        return super.decode(ctx, in);
    }
}
