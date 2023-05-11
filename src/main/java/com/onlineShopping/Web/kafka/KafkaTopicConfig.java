package com.onlineShopping.Web.kafka;//package com.kafka.demo.kafka;
//
//import org.apache.kafka.clients.admin.AdminClientConfig;
//import org.apache.kafka.clients.admin.NewTopic;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.kafka.core.KafkaAdmin;
//import org.springframework.kafka.core.KafkaTemplate;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@Configuration
//public class KafkaTopicConfig {
//
//    @Autowired
//    private KafkaTemplate<String,String> kafkaTemplate;
//
//    @Value(value = "${spring.kafka.bootstrap-servers}")
//    private String bootstrapAddress;
//
////    @Bean
////    public ProducerFactory<String,String> producerFactory(){
////        Map<String,Object> configs = new HashMap<>();
////        configs.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,bootstrapAddress);
////        configs.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
////        configs.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,StringSerializer.class);
////        return new DefaultKafkaProducerFactory<>(configs);
////    }
////
////    @Bean
////    public KafkaTemplate<String,String> kafkaTemplate(){
////        return new KafkaTemplate<>(producerFactory());
////    }
//
//
////    public void sendMessage(String msg){
////        CompletableFuture<SendResult<String, String>> future = kafkaTemplate.send("test-topic", msg).completable();
////        future.whenComplete((result,ex)->{
////            if(ex==null){
////                System.out.println("Sent message=[" + msg +
////                        "] with offset=[" + result.getRecordMetadata().offset() + "]");
////            }else {
////                System.out.println("Unable to send message=[" +
////                        msg + "] due to : " + ex.getMessage());
////            }
////        });
////    }
//
//    @Bean
//    public KafkaAdmin kafkaAdmin(){
//        Map<String,Object> configs = new HashMap<>();
//        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG,bootstrapAddress);
//        return new KafkaAdmin(configs);
//    }
//
//    @Bean
//    public NewTopic topics1(){
//        return new NewTopic("test-topic",1,(short) 1);
//    }
//
//}
