package com.suziesta.BulkOrder.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableAsync
public class AppConfig {

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

//    @Bean
//    public NotificationMessagingTemplate notificationMessagingTemplate(AmazonSNS amazonSNS){
//        return new NotificationMessagingTemplate(amazonSNS);
//    }

}
