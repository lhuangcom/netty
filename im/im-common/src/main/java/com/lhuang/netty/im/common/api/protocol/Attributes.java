package com.lhuang.netty.im.common.api.protocol;

import com.lhuang.netty.im.common.api.pojo.Session;
import io.netty.util.AttributeKey;

/**
 * @author LHuang
 * @since 2019/5/14
 */
public interface Attributes {

    AttributeKey<Boolean> LOGIN = AttributeKey.newInstance("login");

    AttributeKey<Session> SESSION = AttributeKey.newInstance("session");
}
