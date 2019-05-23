package com.lhuang.blog.chatroom.api.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author LHuang
 * @since 2019/5/20
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Session {

    /**
     * 用户唯一的标识
     */
    private String userID;

    private String userName;




}
