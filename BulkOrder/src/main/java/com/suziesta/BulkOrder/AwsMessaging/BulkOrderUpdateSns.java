package com.suziesta.BulkOrder.AwsMessaging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.core.NotificationMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class BulkOrderUpdateSns {

    private final NotificationMessagingTemplate notificationMessagingTemplate;

    @Value("${bulk.order.update.topic}")
    String topic;

    @Autowired
    public BulkOrderUpdateSns(NotificationMessagingTemplate notificationMessagingTemplate) {
        this.notificationMessagingTemplate = notificationMessagingTemplate;
    }

    public void send(String subject, String message) {
        this.notificationMessagingTemplate.sendNotification(topic, message, subject);
    }
}
