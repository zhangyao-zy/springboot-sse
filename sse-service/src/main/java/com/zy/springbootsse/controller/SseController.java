package com.zy.springbootsse.controller;

import com.zy.springbootsse.service.SseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

/**
 * @Author: Zy
 * @Date: 2022/8/30 11:24
 */
@RestController
@RequestMapping("/sse")
@RequiredArgsConstructor
@CrossOrigin
public class SseController {

    private final SseService sseService;

    @GetMapping("/connect")
    public SseEmitter connect(String userId){
        return sseService.createConnect(userId);
    }

    @GetMapping("/close")
    public void closeConnect(String userId){
        sseService.closeConnect(userId);
    }

    @GetMapping("/users")
    public Long getConnectCount(){
        return sseService.getConnectDetail();
    }

}
