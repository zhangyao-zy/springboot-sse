package com.zy.springbootsse.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: Zy
 * @Date: 2022/9/1 11:11
 * @Describe: rabbitmq配置类
 */
@Configuration
public class RabbitMqConfig {
    @Bean
    public FanoutExchange exchange() {
        return new FanoutExchange("TEST");
    }

    @Bean
    public Queue queue() {
        return new Queue("test.queue");
    }

    @Bean
    public Binding binding() {
        return new Binding("test.queue", Binding.DestinationType.QUEUE, "TEST", "", null);
    }

}
