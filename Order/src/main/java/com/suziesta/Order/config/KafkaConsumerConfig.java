package com.suziesta.Order.config;

import com.suziesta.Order.model.Order;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
public class KafkaConsumerConfig {

//    @Value("{spring.kafka.consumer.bootstrap-servers}")
//    private String kafkaServer;
    private String kafkaServer= "localhost:9092";

    @Value("{kafka.consumer.group.id1}")
    private String kafkaGroupId;

    
    @Bean
    public ConsumerFactory<String, Order> userConsumerConfig(){
        Map<String,Object> config = new HashMap<>();
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaServer);
        config.put(ConsumerConfig.GROUP_ID_CONFIG, kafkaGroupId);
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        config.put(JsonDeserializer.TRUSTED_PACKAGES, "*");

        return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(), new JsonDeserializer<>(Order.class,false));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String,Order> userKafkaListenerContainerFactory(){
        ConcurrentKafkaListenerContainerFactory<String,Order> listener = new ConcurrentKafkaListenerContainerFactory<>();
        listener.setConsumerFactory(userConsumerConfig());
        return listener;
    }


}
