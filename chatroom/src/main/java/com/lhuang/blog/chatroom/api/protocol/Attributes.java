package com.lhuang.blog.chatroom.api.protocol;

import io.netty.util.AttributeKey;

/**
 * @author LHuang
 * @since 2019/5/14
 */
public interface Attributes {

    AttributeKey<Boolean> LOGIN = AttributeKey.newInstance("login");
}
