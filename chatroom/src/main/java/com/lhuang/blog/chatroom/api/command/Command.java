package com.lhuang.blog.chatroom.api.command;

public interface Command {

    /**
     * 登录请求
     */
    Byte LOGIN_REQUEST = 1;
    /**
     * 登录响应
     */
    Byte LOGIN_RESPONSE = 2;
    /**
     * 消息请求
     */
    Byte MESSAGE_REQUEST = 3;
    /**
     * 单聊响应、消息响应
     */
    Byte MESSAGE_RESPONSE = 4;
    /**
     * 单聊请求
     */
    Byte MESSAGE_FORWARD = 5;
    /**
     * 建立群聊请求
     */
    Byte CREATE_GROUP_REQUEST = 6;
    /**
     * 建立群聊响应
     */
    Byte CREATE_GROUP_RESPONSE = 7;
    /**
     * 登出请求
     */
    Byte LOGOUT_REQUEST = 8;
    /**
     * 登录响应
     */
    Byte LOGOUT_RESPONSE = 9;

   // Byte SEND_TO_USER_REQUEST = 10;

   // Byte SEND_TO_USER_RESPONSE = 11;
    /**
     * 加入群聊请求
     */
    Byte JOIN_GROUP_REQUEST = 12;
    /**
     * 加入群聊响应
     */
    Byte JOIN_GROUP_RESPOMSE = 13;
    /**
     * 群聊成员列表请求
     */
    Byte LIST_GROUP_MEMBERS_REQUEST = 14;
    /**
     * 群聊成员列表响应
     */
    Byte LIST_GROUP_MEMBERS_RESPONSE = 15;
    /**
     * 退出群聊请求
     */
    Byte QUIT_GROUP_REQUEST = 16;
    /**
     * 退出群聊响应
     */
    Byte QUIT_GROUP_RESPONSE = 17;

    /**
     * 发送群消息请求
     */
    Byte GROUP_MESSAGE_REQUEST = 18;

    /**
     * 发送群消息响应
     */
    Byte  GROUP_MESSAGE_RESPONSE = 19;

    /**
     * 心跳检测请求
     */
    Byte  HEARTBEAT_REQUEST = 20;
    /**
     * 心跳检测响应
     */
    Byte HEARTBEAT_RESPONSE = 21;



}
