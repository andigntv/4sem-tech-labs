package com.bickbrother.cars.rabbit;

import com.bickbrother.data.dtos.CarDTO;
import com.bickbrother.messages.configurations.CarConf;
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
public class CarListener {
    private final ObjectMapper objectMapper;
    private final RabbitTemplate rabbitTemplate;
    private final CrudServiceInterface<CarDTO> carService;

    @RabbitListener(queues = CarConf.SEND_QUEUE_NAME)
    public void handleMessage(Message message) {
        String operation = message.getMessageProperties().getHeader(MessageService.OPERATION_KEY);
        switch (operation) {
            case (MessageService.SAVE_HEADER) -> sendAnswer(carService.save(read(message, CarDTO.class)));
            case (MessageService.GET_HEADER) -> sendAnswer(carService.getById(read(message, Long.class)));
            case (MessageService.UPDATE_HEADER) -> sendAnswer(carService.update(read(message, CarDTO.class)));
            case (MessageService.DELETE_HEADER) -> carService.deleteById(read(message, Long.class));
        }
    }

    private <T> T read(Message message, Class<T> type) {
        try {
            return objectMapper.readValue(message.getBody(), type);
        } catch (IOException e) {
            throw new RuntimeException("Failed parsing bytes to DTO" + e);
        }
    }

    private void sendAnswer(CarDTO carDTO) {
        rabbitTemplate.convertAndSend(CarConf.EXCHANGE_NAME, CarConf.RESULT_ROUTING_KEY, carDTO);
    }
}
