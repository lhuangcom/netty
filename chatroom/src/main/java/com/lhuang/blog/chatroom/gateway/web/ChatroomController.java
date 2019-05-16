package com.lhuang.blog.chatroom.gateway.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;


@Controller
@RequestMapping("/chatroom")
public class ChatroomController {

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping("/chat")
    public String chat(@RequestParam(value = "username",required = false) String username, ModelAndView mv, HttpServletRequest request){
        if(username == null || "".equals(username) ){
           // mv.setViewName("login");
            return "login";
        }
        /*Message message = new Message();
        message.setUsername(username);
        mv.addObject("message",message);
        mv.setViewName("chat");*/
        String uuid = UUID.randomUUID().toString();
        request.setAttribute("userId",uuid);
        request.setAttribute("username",username);
        return "chat";
    }
}
