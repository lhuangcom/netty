package com.lhuang.netty.im.common.api.factory;


import com.lhuang.netty.im.common.api.protocol.packet.Packet;
import com.lhuang.netty.im.common.api.protocol.packet.request.*;
import com.lhuang.netty.im.common.api.protocol.packet.response.*;
import com.lhuang.netty.im.common.api.protocol.serialize.JSONSerializer;
import com.lhuang.netty.im.common.api.protocol.serialize.Serializer;
import io.netty.buffer.ByteBuf;

import java.util.HashMap;
import java.util.Map;

import static com.lhuang.netty.im.common.api.command.Command.*;


public class PacketCode {

    public static final int MAGIC_NUMBER = 0x12345678;

    private  final Map<Byte, Class<? extends Packet>> packetTypeMap;
    private  final Map<Byte, Serializer> serializerMap;



    private PacketCode(){
        packetTypeMap = new HashMap<>();
        packetTypeMap.put(LOGIN_REQUEST, LoginRequestPacket.class);
        packetTypeMap.put(LOGIN_RESPONSE, LoginResponsePacket.class);
        packetTypeMap.put(MESSAGE_REQUEST, MessageRequestPacket.class);
        packetTypeMap.put(MESSAGE_RESPONSE, MessageResponsePacket.class);
        packetTypeMap.put(MESSAGE_FORWARD, MessageForwardPacket.class);
        packetTypeMap.put(CREATE_GROUP_REQUEST, CreateGroupRequestPacket.class);
        packetTypeMap.put(CREATE_GROUP_RESPONSE, CreateGroupResponsePacket.class);
        packetTypeMap.put(LOGOUT_REQUEST, LogoutRequestPacket.class);
        packetTypeMap.put(LOGOUT_RESPONSE, LogoutResponsePacket.class);
        packetTypeMap.put(JOIN_GROUP_REQUEST, JoinGroupRequestPacket.class);
        packetTypeMap.put(JOIN_GROUP_RESPOMSE, JoinGroupResponsePacket.class);
        packetTypeMap.put(LIST_GROUP_MEMBERS_REQUEST,ListGroupMembersRequestPacket.class);
        packetTypeMap.put(LIST_GROUP_MEMBERS_RESPONSE,ListGroupMembersResponsePacket.class);
        packetTypeMap.put(QUIT_GROUP_REQUEST,QuitGroupRequestPacket.class);
        packetTypeMap.put(QUIT_GROUP_RESPONSE,QuitGroupResponsePacket.class);
        packetTypeMap.put( GROUP_MESSAGE_REQUEST,GroupMessageRequestPacket.class);
        packetTypeMap.put( GROUP_MESSAGE_RESPONSE,GroupMessageResponsePacket.class);
        packetTypeMap.put(HEARTBEAT_REQUEST, HeartBeatRequestPacket.class);


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

    public ByteBuf encode(ByteBuf byteBuf, Packet packet){

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
