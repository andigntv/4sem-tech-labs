package com.bickbrother.messages.configurations;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EngineConf {
    public static final String EXCHANGE_NAME = "engine-exchange";
    public static final String SEND_QUEUE_NAME = "engine-send-queue";
    public static final String SEND_ROUTING_KEY = "send";
    public static final String RESULT_QUEUE_NAME = "engine-result-queue";
    public static final String RESULT_ROUTING_KEY = "result";

    @Bean
    public DirectExchange engineExchange() {
        return new DirectExchange(EXCHANGE_NAME);
    }

    @Bean
    public Queue engineSendQueue() {
        return new Queue(SEND_QUEUE_NAME, true, false, false);
    }

    @Bean
    public Queue engineResultQueue() {
        return new Queue(RESULT_QUEUE_NAME, true, false, false);
    }


    @Bean
    public Binding engineSendBinding(Queue engineSendQueue, DirectExchange engineExchange) {
        return BindingBuilder.bind(engineSendQueue).to(engineExchange).with(SEND_ROUTING_KEY);
    }

    @Bean
    public Binding engineResultBinding(Queue engineResultQueue, DirectExchange engineExchange) {
        return BindingBuilder.bind(engineResultQueue).to(engineExchange).with(RESULT_ROUTING_KEY);
    }

}