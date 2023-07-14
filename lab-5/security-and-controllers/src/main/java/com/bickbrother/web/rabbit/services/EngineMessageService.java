package com.bickbrother.web.rabbit.services;

import com.bickbrother.data.dtos.EngineDTO;
import com.bickbrother.messages.configurations.EngineConf;
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
public class EngineMessageService implements MessageService<EngineDTO> {

    private final ObjectMapper objectMapper;
    private final RabbitTemplate rabbitTemplate;

    @Override
    public EngineDTO save(EngineDTO engineDTO) {
        rabbitTemplate.convertAndSend(
                EngineConf.EXCHANGE_NAME,
                EngineConf.SEND_ROUTING_KEY,
                engineDTO,
                m -> {
                    m.getMessageProperties().getHeaders().put(OPERATION_KEY, SAVE_HEADER);
                    m.getMessageProperties().setReplyTo(EngineConf.RESULT_QUEUE_NAME);
                    return m;
                });

        Message result = rabbitTemplate.receive(EngineConf.RESULT_QUEUE_NAME, TimeUnit.SECONDS.toMillis(5));
        if (result == null) throw new RuntimeException("Timeout while waiting for response");
        try {
            return objectMapper.readValue(result.getBody(), EngineDTO.class);
        } catch (IOException e) {
            throw new RuntimeException("Failed parsing bytes to DTO");
        }
    }

    @Override
    public EngineDTO getById(Long id) {
        rabbitTemplate.convertAndSend(
                EngineConf.EXCHANGE_NAME,
                EngineConf.SEND_ROUTING_KEY,
                id,
                m -> {
                    m.getMessageProperties().getHeaders().put(OPERATION_KEY, GET_HEADER);
                    m.getMessageProperties().setReplyTo(EngineConf.RESULT_QUEUE_NAME);
                    return m;
                });
        Message result = rabbitTemplate.receive(EngineConf.RESULT_QUEUE_NAME, TimeUnit.SECONDS.toMillis(5));
        if (result == null) throw new RuntimeException("Timeout while waiting for response");
        try {
            return objectMapper.readValue(result.getBody(), EngineDTO.class);
        } catch (IOException e) {
            throw new RuntimeException("Failed parsing bytes to DTO");
        }
    }

    @Override
    public EngineDTO update(EngineDTO engineDTO) {
        rabbitTemplate.convertAndSend(
                EngineConf.EXCHANGE_NAME,
                EngineConf.SEND_ROUTING_KEY,
                engineDTO,
                m -> {
                    m.getMessageProperties().getHeaders().put(OPERATION_KEY, UPDATE_HEADER);
                    m.getMessageProperties().setReplyTo(EngineConf.RESULT_QUEUE_NAME);
                    return m;
                });

        Message result = rabbitTemplate.receive(EngineConf.RESULT_QUEUE_NAME, TimeUnit.SECONDS.toMillis(5));
        if (result == null) throw new RuntimeException("Timeout while waiting for response");
        try {
            return objectMapper.readValue(result.getBody(), EngineDTO.class);
        } catch (IOException e) {
            throw new RuntimeException("Failed parsing bytes to DTO");
        }
    }

    @Override
    public void deleteById(Long id) {
        rabbitTemplate.convertAndSend(
                EngineConf.EXCHANGE_NAME,
                EngineConf.SEND_ROUTING_KEY,
                id,
                m -> {
                    m.getMessageProperties().getHeaders().put(OPERATION_KEY, DELETE_HEADER);
                    m.getMessageProperties().setReplyTo(EngineConf.RESULT_QUEUE_NAME);
                    return m;
                });
    }
}
