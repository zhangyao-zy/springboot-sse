package com.zy.springbootsse.service.impl;

import com.zy.springbootsse.service.SseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: Zy
 * @Date: 2022/8/30 11:28
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class SseServiceImpl implements SseService {

    private final RabbitTemplate rabbitTemplate;

    ConcurrentHashMap<String, SseEmitter> cache = new ConcurrentHashMap<String, SseEmitter>();

    @Override
    public SseEmitter createConnect(String userId) {
        SseEmitter sseEmitter = new SseEmitter(0L);

        cache.put(userId, sseEmitter);
        try {
            sseEmitter.send(SseEmitter.event().id("cust").data("test" + userId));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sseEmitter;
    }

    @Override
    public void closeConnect(String userId) {
        SseEmitter sseEmitter = cache.get(userId);
        if (sseEmitter != null) {
            sseEmitter.complete();
            cache.remove(userId);
        }
    }

    @Override
    public void sendMsgToClient(String userId, String id, Object data) {
        if (StringUtils.isEmpty(userId)) {
            cache.forEach((item, sseEmitter) -> {
                SseEmitter.SseEventBuilder eventBuilder = SseEmitter.event().id(id).data(data);
                sendMsg(sseEmitter, eventBuilder);
            });
        } else {
            SseEmitter sseEmitter = cache.get(userId);
            if (sseEmitter == null) {
                throw new RuntimeException("长连接为空");
            }
            SseEmitter.SseEventBuilder eventBuilder = SseEmitter.event().id(id).data(data);
            sendMsg(sseEmitter, eventBuilder);
        }
    }

    private void sendMsg(SseEmitter sseEmitter, SseEmitter.SseEventBuilder eventBuilder) {
        try {
            sseEmitter.send(eventBuilder);
        } catch (IOException e) {
            e.printStackTrace();
            log.error("推送消息客户端失败");
        }
    }

    @Override
    public void sendMsgToClientByMultiNode(String userId, Object data) {
        rabbitTemplate.convertAndSend("TEST", "TEST", data);
    }

    @Override
    public Long getConnectDetail() {
        return (long) cache.size();
    }

}
