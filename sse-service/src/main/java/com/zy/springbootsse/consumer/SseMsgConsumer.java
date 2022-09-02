package com.zy.springbootsse.consumer;

import com.zy.springbootsse.service.SseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Author: Zy
 * @Date: 2022/9/1 11:17
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class SseMsgConsumer {

    private final SseService sseService;



    @RabbitListener(queues = "#{queue.name}")
    public void consumerSseMsg(String data) {
        log.info(data);
        sseService.sendMsgToClient(null,null,data);
    }

}
