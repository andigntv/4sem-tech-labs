package com.bickbrother.web.rabbit.services;

import com.bickbrother.data.dtos.BrandDTO;
import com.bickbrother.messages.configurations.BrandConf;
import com.bickbrother.messages.interfaces.MessageService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
@Service
public class BrandMessageService implements MessageService<BrandDTO> {

    private final ObjectMapper objectMapper;
    private final RabbitTemplate rabbitTemplate;

    @Override
    public BrandDTO save(BrandDTO brandDTO) {
        rabbitTemplate.convertAndSend(
                BrandConf.EXCHANGE_NAME,
                BrandConf.SEND_ROUTING_KEY,
                brandDTO,
                m -> {
                    m.getMessageProperties().getHeaders().put(OPERATION_KEY, SAVE_HEADER);
                    m.getMessageProperties().setReplyTo(BrandConf.RESULT_QUEUE_NAME);
                    return m;
                });

        Message result = rabbitTemplate.receive(BrandConf.RESULT_QUEUE_NAME, TimeUnit.SECONDS.toMillis(5));
        if (result == null) throw new RuntimeException("Timeout while waiting for response");
        try {
            return objectMapper.readValue(result.getBody(), BrandDTO.class);
        } catch (IOException e) {
            throw new RuntimeException("Failed parsing bytes to DTO");
        }
    }

    @Override
    public BrandDTO getById(Long id) {
        rabbitTemplate.convertAndSend(
                BrandConf.EXCHANGE_NAME,
                BrandConf.SEND_ROUTING_KEY,
                id,
                m -> {
                    m.getMessageProperties().getHeaders().put(OPERATION_KEY, GET_HEADER);
                    m.getMessageProperties().setReplyTo(BrandConf.RESULT_QUEUE_NAME);
                    return m;
                });
        Message result = rabbitTemplate.receive(BrandConf.RESULT_QUEUE_NAME, TimeUnit.SECONDS.toMillis(5));
        if (result == null) throw new RuntimeException("Timeout while waiting for response");
        try {
            return objectMapper.readValue(result.getBody(), BrandDTO.class);
        } catch (IOException e) {
            throw new RuntimeException("Failed parsing bytes to DTO");
        }
    }

    @Override
    public BrandDTO update(BrandDTO brandDTO) {
        rabbitTemplate.convertAndSend(
                BrandConf.EXCHANGE_NAME,
                BrandConf.SEND_ROUTING_KEY,
                brandDTO,
                m -> {
                    m.getMessageProperties().getHeaders().put(OPERATION_KEY, UPDATE_HEADER);
                    m.getMessageProperties().setReplyTo(BrandConf.RESULT_QUEUE_NAME);
                    return m;
                });

        Message result = rabbitTemplate.receive(BrandConf.RESULT_QUEUE_NAME, TimeUnit.SECONDS.toMillis(5));
        if (result == null) throw new RuntimeException("Timeout while waiting for response");
        try {
            return objectMapper.readValue(result.getBody(), BrandDTO.class);
        } catch (IOException e) {
            throw new RuntimeException("Failed parsing bytes to DTO");
        }
    }

    @Override
    public void deleteById(Long id) {
        rabbitTemplate.convertAndSend(
                BrandConf.EXCHANGE_NAME,
                BrandConf.SEND_ROUTING_KEY,
                id,
                m -> {
                    m.getMessageProperties().getHeaders().put(OPERATION_KEY, DELETE_HEADER);
                    m.getMessageProperties().setReplyTo(BrandConf.RESULT_QUEUE_NAME);
                    return m;
                });
    }
}
