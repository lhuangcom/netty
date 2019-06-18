package com.lhuang.netty.im.client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConsumerController {


    @Autowired
    private IMServerRemote imServerRemote;


    @RequestMapping("/hello")
    public String index() {
        return imServerRemote.getServerInfo().toString();
    }



}
