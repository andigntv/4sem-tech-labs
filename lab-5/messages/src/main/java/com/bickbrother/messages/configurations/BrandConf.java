package com.bickbrother.messages.configurations;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BrandConf {
    public static final String EXCHANGE_NAME = "brand-exchange";
    public static final String SEND_QUEUE_NAME = "brand-send-queue";
    public static final String SEND_ROUTING_KEY = "send";
    public static final String RESULT_QUEUE_NAME = "brand-result-queue";
    public static final String RESULT_ROUTING_KEY = "result";

    @Bean
    public DirectExchange brandExchange() {
        return new DirectExchange(EXCHANGE_NAME);
    }

    @Bean
    public Queue brandSendQueue() {
        return new Queue(SEND_QUEUE_NAME, true, false, false);
    }

    @Bean
    public Queue brandResultQueue() {
        return new Queue(RESULT_QUEUE_NAME, true, false, false);
    }


    @Bean
    public Binding brandSendBinding(Queue brandSendQueue, DirectExchange brandExchange) {
        return BindingBuilder.bind(brandSendQueue).to(brandExchange).with(SEND_ROUTING_KEY);
    }

    @Bean
    public Binding brandResultBinding(Queue brandResultQueue, DirectExchange brandExchange) {
        return BindingBuilder.bind(brandResultQueue).to(brandExchange).with(RESULT_ROUTING_KEY);
    }

}
