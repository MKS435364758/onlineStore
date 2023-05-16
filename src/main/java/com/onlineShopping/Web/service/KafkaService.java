package com.onlineShopping.Web.service;

import com.onlineShopping.Web.kafka.KafkaProducerConfig;
import com.onlineShopping.Web.kafka.message.request.OrderInfoMessageRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class KafkaService {

    private KafkaProducerConfig kafkaProducerConfig;

    public void sendMessage(String key, OrderInfoMessageRequest message) {
        kafkaProducerConfig.sendMessage(key, message);
    }


}
