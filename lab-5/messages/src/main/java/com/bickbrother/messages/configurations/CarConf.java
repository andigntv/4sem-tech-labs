package com.bickbrother.messages.configurations;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CarConf {
    public static final String EXCHANGE_NAME = "car-exchange";
    public static final String SEND_QUEUE_NAME = "car-send-queue";
    public static final String SEND_ROUTING_KEY = "send";
    public static final String RESULT_QUEUE_NAME = "car-result-queue";
    public static final String RESULT_ROUTING_KEY = "result";

    @Bean
    public DirectExchange carExchange() {
        return new DirectExchange(EXCHANGE_NAME);
    }

    @Bean
    public Queue carSendQueue() {
        return new Queue(SEND_QUEUE_NAME, true, false, false);
    }

    @Bean
    public Queue carResultQueue() {
        return new Queue(RESULT_QUEUE_NAME, true, false, false);
    }


    @Bean
    public Binding carSendBinding(Queue carSendQueue, DirectExchange carExchange) {
        return BindingBuilder.bind(carSendQueue).to(carExchange).with(SEND_ROUTING_KEY);
    }

    @Bean
    public Binding carResultBinding(Queue carResultQueue, DirectExchange carExchange) {
        return BindingBuilder.bind(carResultQueue).to(carExchange).with(RESULT_ROUTING_KEY);
    }

}