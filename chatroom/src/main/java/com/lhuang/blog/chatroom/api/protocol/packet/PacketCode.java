package com.lhuang.blog.chatroom.api.protocol.packet;

import com.lhuang.blog.chatroom.api.protocol.serialize.JSONSerializer;
import com.lhuang.blog.chatroom.api.protocol.serialize.Serializer;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

import java.util.HashMap;
import java.util.Map;

import static com.lhuang.blog.chatroom.api.protocol.Command.*;


public class PacketCode {

    public static final int MAGIC_NUMBER = 0x12345678;

    private  final Map<Byte, Class<? extends com.lhuang.blog.chatroom.api.protocol.packet.Packet>> packetTypeMap;
    private  final Map<Byte, Serializer> serializerMap;



    private PacketCode(){
        packetTypeMap = new HashMap<>();
        packetTypeMap.put(LOGIN_REQUEST, com.lhuang.blog.chatroom.api.protocol.packet.LoginRequestPacket.class);
        packetTypeMap.put(LOGIN_RESPONSE, com.lhuang.blog.chatroom.api.protocol.packet.LoginResponsePacket.class);
        packetTypeMap.put(MESSAGE_REQUEST, com.lhuang.blog.chatroom.api.protocol.packet.MessageRequestPacket.class);
        packetTypeMap.put(MESSAGE_RESPONSE, com.lhuang.blog.chatroom.api.protocol.packet.MessageResponsePacket.class);


        serializerMap = new HashMap<>();
        Serializer serializer = new JSONSerializer();
        serializerMap.put(serializer.getSerializerAlogrithm(), serializer);
    }

    private static class PackCodeInner{
        private static PacketCode packetCodeInstance = new PacketCode();

    }

    public static PacketCode getInstance(){
        return PackCodeInner.packetCodeInstance;
    }

    public ByteBuf encode(ByteBuf byteBuf,Packet packet){

        //序列化Java对象
        byte[] bytes = Serializer.DEFAULT.serializer(packet);

        //实际编码过程
        byteBuf.writeInt(MAGIC_NUMBER);
        byteBuf.writeByte(packet.getVersion());
        byteBuf.writeByte(Serializer.JSON_SERIALIZER);
        byteBuf.writeByte(packet.getCommand());
        byteBuf.writeInt(bytes.length);
        byteBuf.writeBytes(bytes);

        return byteBuf;

    }

    public Packet decode(ByteBuf byteBuf){
        //跳过magic number
        byteBuf.skipBytes(4);

        //跳过版本号
        byteBuf.skipBytes(1);

        //序列化算法标识
        byte serializeAlogithm = byteBuf.readByte();

        //指令
        byte command = byteBuf.readByte();

        // 数据包长度
        int length = byteBuf.readInt();

        byte[] bytes = new byte[length];

        //读取数据内容
        byteBuf.readBytes(bytes);

        Class<? extends Packet> requestType = getRequestType(command);

        Serializer serializer = getSerializer(serializeAlogithm);

        if (requestType != null && serializer != null){
            return serializer.derializer(requestType,bytes);
        }

        return null;

    }



    private Serializer getSerializer(byte serializeAlgorithm) {

        return serializerMap.get(serializeAlgorithm);
    }

    private Class<? extends Packet> getRequestType(byte command) {

        return packetTypeMap.get(command);
    }
}
