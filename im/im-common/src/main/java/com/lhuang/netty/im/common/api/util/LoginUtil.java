package com.lhuang.netty.im.common.api.util;

import com.lhuang.netty.im.common.api.protocol.Attributes;
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

    public static void markLoginout(Channel channel){
        channel.attr(Attributes.LOGIN).set(false);
    }


    public static Boolean hasLogin(Channel channel){
        Attribute<Boolean> loginAttr = channel.attr(Attributes.LOGIN);
        if (loginAttr.get() != null){
            return loginAttr.get();
        }
        return false;

    }

}
