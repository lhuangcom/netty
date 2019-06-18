package com.lhuang.netty.im.common.api.util;

import com.lhuang.netty.im.common.api.pojo.Session;
import com.lhuang.netty.im.common.api.protocol.Attributes;
import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author LHuang
 * @since 2019/5/20
 */
public class SessionUtil {

    public static final Map<String, Channel> userIdChannelMap = new ConcurrentHashMap<>();

    public static final Map<String, ChannelGroup> groupIdChannelGroupMap = new ConcurrentHashMap<>();

    public static void bindSession(Session session, Channel channel){

        userIdChannelMap.put(session.getUserID(),channel);

        channel.attr(Attributes.SESSION).set(session);

    }

    public static void unBindSession(Channel channel){

        if (hasLogin(channel)){
            userIdChannelMap.remove(getSession(channel));
            channel.attr(Attributes.SESSION).set(null);
        }

    }

    public static void bindChannelGroup(String groupId, ChannelGroup channelGroup) {
        groupIdChannelGroupMap.put(groupId, channelGroup);
    }

    public static ChannelGroup getChannelGroup(String groupId) {
        return groupIdChannelGroupMap.get(groupId);
    }

    public static boolean hasLogin(Channel channel){

        return  channel.hasAttr(Attributes.SESSION);
    }

    public static Session getSession(Channel channel) {

        return channel.attr(Attributes.SESSION).get();
    }

    public static Channel getChannel(String userId) {

        return userIdChannelMap.get(userId);
    }


}