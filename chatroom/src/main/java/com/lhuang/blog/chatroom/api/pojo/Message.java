package com.lhuang.blog.chatroom.api.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message {


    public static final String ENTER = "enter";
    public static final String EXIT = "exit";
    public static final String SPEAK= "speak";

    private String state;
    private String fromUsername;
    private String toUserId;
    private String text;
    private int onlineCount;




}
