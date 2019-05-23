package com.lhuang.blog.chatroom.api.util;

import java.util.UUID;

/**
 * @author LHuang
 * @since 2019/5/20
 */
public class IDUtil {
    public static String randomID(){
        return UUID.randomUUID().toString().split("-")[0];
    }

}
