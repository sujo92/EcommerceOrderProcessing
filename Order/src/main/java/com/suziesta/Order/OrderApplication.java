package com.suziesta.Order;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.suziesta.Order.aws.BulkOrderListnerSqs;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class OrderApplication {

	public static void main(String[] args) throws JsonProcessingException {

		ConfigurableApplicationContext context = SpringApplication.run(OrderApplication.class, args);

//		System.out.println("listen to bulk update");
//		BulkOrderUpdateListnerSqs bulkOrderUpdateListnerSqs = context.getBean(BulkOrderUpdateListnerSqs.class);
//		bulkOrderUpdateListnerSqs.startListeningToMessages();

		BulkOrderListnerSqs bulkOrderListnerSqs = context.getBean(BulkOrderListnerSqs.class);
		bulkOrderListnerSqs.startListeningToMessages();
	}

}
