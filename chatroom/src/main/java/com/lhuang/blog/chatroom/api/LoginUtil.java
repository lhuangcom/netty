package com.lhuang.blog.chatroom.api;

import com.lhuang.blog.chatroom.api.protocol.Attributes;
import io.netty.channel.Channel;
import io.netty.util.Attribute;

/**
 * @author LHuang
 * @since 2019/5/14
 */
public class LoginUtil {

    public static void markLogin(Channel channel){
        channel.attr(Attributes.LOGIN).set(true);
    }


    public static Boolean hasLogin(Channel channel){

        Attribute<Boolean> loginAttr = channel.attr(Attributes.LOGIN);
        return loginAttr.get() != null;
    }

}
