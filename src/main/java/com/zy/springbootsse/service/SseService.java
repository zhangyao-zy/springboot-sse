package com.zy.springbootsse.service;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

/**
 * @Author: Zy
 * @Date: 2022/8/30 11:25
 */
public interface SseService {

    /**
     * 创建sse长连接
     *
     * @param userId 唯一标识
     * @return org.springframework.web.servlet.mvc.method.annotation.SseEmitter
     * @author Zy
     * @date 2022/8/30
     */
    SseEmitter createConnect(String userId);

    /**
     * 关闭sse长连接
     *
     * @param userId 唯一标识
     * @author Zy
     * @date 2022/8/30
     */
    void closeConnect(String userId);


    /**
     * 给客户端推送消息
     *
     * @param userId 用户id 唯一标识
     * @param id     消息id
     * @param data   消息数据
     * @author Zy
     * @date 2022/8/30
     */
    void sendMsgToClient(String userId, String id, Object data);


}
