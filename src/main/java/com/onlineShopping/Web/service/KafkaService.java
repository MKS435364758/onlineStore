package com.onlineShopping.Web.service;

import com.onlineShopping.Web.kafka.KafkaProducerConfig;
import com.onlineShopping.Web.pojo.OrderInfo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class KafkaService {

    private KafkaProducerConfig kafkaProducerConfig;

    public void sendMessage(String key, OrderInfo message) {
        kafkaProducerConfig.sendMessage(key, message);
    }


}
