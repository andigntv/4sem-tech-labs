package com.bickbrother.brands.rabbit;

import com.bickbrother.data.dtos.BrandDTO;
import com.bickbrother.messages.configurations.BrandConf;
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
public class BrandListener {
    private final ObjectMapper objectMapper;
    private final RabbitTemplate rabbitTemplate;
    private final CrudServiceInterface<BrandDTO> brandService;

    @RabbitListener(queues = BrandConf.SEND_QUEUE_NAME)
    public void handleMessage(Message message) {
        String operation = message.getMessageProperties().getHeader(MessageService.OPERATION_KEY);
        switch (operation) {
            case (MessageService.SAVE_HEADER) -> sendAnswer(brandService.save(read(message, BrandDTO.class)));
            case (MessageService.GET_HEADER) -> sendAnswer(brandService.getById(read(message, Long.class)));
            case (MessageService.UPDATE_HEADER) -> sendAnswer(brandService.update(read(message, BrandDTO.class)));
            case (MessageService.DELETE_HEADER) -> brandService.deleteById(read(message, Long.class));
        }
    }

    private <T> T read(Message message, Class<T> type) {
        try {
            return objectMapper.readValue(message.getBody(), type);
        } catch (IOException e) {
            throw new RuntimeException("Failed parsing bytes to DTO" + e);
        }
    }

    private void sendAnswer(BrandDTO brandDTO) {
        rabbitTemplate.convertAndSend(BrandConf.EXCHANGE_NAME, BrandConf.RESULT_ROUTING_KEY, brandDTO);
    }
}
