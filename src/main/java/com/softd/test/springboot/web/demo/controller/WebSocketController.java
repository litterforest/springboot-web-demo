package com.softd.test.springboot.web.demo.controller;

import com.softd.test.springboot.web.demo.exception.WebSocketOprException;
import com.softd.test.springboot.web.demo.websocket.WebSocketServer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

/**
 * 功能描述
 *
 * @author cobee
 * @since 2020-10-04
 */
@Controller
@RequestMapping("/websocket")
public class WebSocketController {
    @RequestMapping("")
    public String websocket() {
        return "websocket";
    }

    @RequestMapping("/getOnlineUserNums")
    @ResponseBody
    public String getOnlineUserNums() {
        return String.valueOf(WebSocketServer.getOnlineCount());
    }

    @RequestMapping("/sendMsg/{userId}")
    @ResponseBody
    public String sendMsg(@PathVariable("userId") String userId, @RequestParam("msg") String msg) throws IOException, WebSocketOprException {
        WebSocketServer.sendMsgToUser(userId, msg);
        return "success";
    }
}
