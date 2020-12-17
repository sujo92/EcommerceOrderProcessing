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
public class UpdateUsetKafkaConfig {

        private String kafkaServer= "localhost:9092";

        @Value("{kafka.consumer.group.id2}")
        private String kafkaGroupId;


        @Bean
        public ConsumerFactory<String, Order> updateUserConsumerConfig(){
            Map<String,Object> config1 = new HashMap<>();
            config1.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaServer);
            config1.put(ConsumerConfig.GROUP_ID_CONFIG, kafkaGroupId);
            config1.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
            config1.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
            config1.put(JsonDeserializer.TRUSTED_PACKAGES, "*");

            return new DefaultKafkaConsumerFactory<>(config1, new StringDeserializer(), new JsonDeserializer<>(Order.class,false));
        }

        @Bean
        public ConcurrentKafkaListenerContainerFactory<String,Order> updateUserKafkaListenerContainerFactory(){
            ConcurrentKafkaListenerContainerFactory<String,Order> listener = new ConcurrentKafkaListenerContainerFactory<>();
            listener.setConsumerFactory(updateUserConsumerConfig());
            return listener;
        }


}

