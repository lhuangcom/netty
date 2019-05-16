package com.lhuang.blog.chatroom.api.protocol.serialize;

import com.alibaba.fastjson.JSON;

public class JSONSerializer implements Serializer {


    @Override
    public byte getSerializerAlogrithm() {
        return SerializerAlgorithm.JSON;
    }

    @Override
    public byte[] serializer(Object object) {

        return JSON.toJSONBytes(object);
    }

    @Override
    public <T> T derializer(Class<T> clazz, byte[] bytes) {
        return JSON.parseObject(bytes,clazz);
    }
}
