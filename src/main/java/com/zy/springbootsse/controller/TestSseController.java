package com.zy.springbootsse.controller;

import com.zy.springbootsse.service.SseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Zy
 * @Date: 2022/8/30 14:33
 */
@RestController
@RequestMapping("test-sse")
@RequiredArgsConstructor
public class TestSseController {

    private final SseService sseService;


    @GetMapping
    public void testSse(String userId, String msgId, String data) {
        sseService.sendMsgToClient(userId, msgId, data);
    }
}
