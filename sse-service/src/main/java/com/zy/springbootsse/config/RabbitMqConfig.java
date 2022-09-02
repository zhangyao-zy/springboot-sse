package com.zy.springbootsse.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: Zy
 * @Date: 2022/9/1 11:11
 * @Describe: rabbitmq配置类
 */
@Configuration
public class RabbitMqConfig {

    @Value("${server.port}")
    private String port;

    @Bean
    public FanoutExchange exchange() {
        return new FanoutExchange("TEST");
    }

    @Bean
    public Queue queue() {
        return new Queue("test.queue"+port);
    }

    @Bean
    public Binding binding() {
        return new Binding("test.queue"+port, Binding.DestinationType.QUEUE, "TEST", "", null);
    }

}
