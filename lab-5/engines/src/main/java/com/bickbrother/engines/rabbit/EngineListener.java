package com.bickbrother.engines.rabbit;

import com.bickbrother.data.dtos.EngineDTO;
import com.bickbrother.messages.configurations.EngineConf;
import com.bickbrother.messages.interfaces.MessageService;
import com.bickbrother.utils.interfaces.CrudServiceInterface;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
@EnableRabbit
public class EngineListener {
    private final ObjectMapper objectMapper;
    private final RabbitTemplate rabbitTemplate;
    private final CrudServiceInterface<EngineDTO> engineService;

    @RabbitListener(queues = EngineConf.SEND_QUEUE_NAME)
    public void handleMessage(Message message) {
        String operation = message.getMessageProperties().getHeader(MessageService.OPERATION_KEY);
        switch (operation) {
            case (MessageService.SAVE_HEADER) -> sendAnswer(engineService.save(read(message, EngineDTO.class)));
            case (MessageService.GET_HEADER) -> sendAnswer(engineService.getById(read(message, Long.class)));
            case (MessageService.UPDATE_HEADER) -> sendAnswer(engineService.update(read(message, EngineDTO.class)));
            case (MessageService.DELETE_HEADER) -> engineService.deleteById(read(message, Long.class));
        }
    }

    private <T> T read(Message message, Class<T> type) {
        try {
            return objectMapper.readValue(message.getBody(), type);
        } catch (IOException e) {
            throw new RuntimeException("Failed parsing bytes to DTO" + e);
        }
    }

    private void sendAnswer(EngineDTO engineDTO) {
        rabbitTemplate.convertAndSend(EngineConf.EXCHANGE_NAME, EngineConf.RESULT_ROUTING_KEY, engineDTO);
    }
}
