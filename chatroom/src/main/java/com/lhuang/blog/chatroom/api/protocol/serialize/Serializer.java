package com.lhuang.blog.chatroom.api.protocol.serialize;

public interface Serializer {

    /**
      * json 序列化
     */
    byte JSON_SERIALIZER = 1;

    /**
     * 默认的序列化标识
     */
    Serializer DEFAULT = new JSONSerializer();


    /**
     * 序列化算法
     * @return
     */
    byte getSerializerAlogrithm();

    /**
     * 对象转成二进制
     * @return
     */
    byte[] serializer(Object object);

    /**
     * 反序列化
     */
    <T>T derializer(Class<T> clazz, byte[] bytes);

}
