package com.suziesta.Order.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.suziesta.Order.model.Order;
import com.suziesta.Order.repository.OrderRepository;
import com.suziesta.Order.util.getOrderData;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@SpringBootTest( webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@ActiveProfiles("integrationtest")
public class OrderControllerTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private OrderRepository orderRepository;

    @Before
    public void setup(){
        orderRepository.save(getOrderData.getOrder());
    }

    @After
    public void cleanup(){
        orderRepository.deleteAll();
    }

    @Test
    public void createOrder() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        Order newOrder = getOrderData.getOrder2();

        mvc.perform(MockMvcRequestBuilders.post("/app/order")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(newOrder))
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.is(true)));
    }

    @Test
    public void getOrder() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/app/order/0ccf443c-c7d5-4c14-90e4-3133283a4ed7"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.customerId",Matchers.is(7)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.total",Matchers.is(500.0)));
    }

    @Test
    public void updateOrder() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        Order order = getOrderData.getOrder();
        order.setCustomerId(58);

        mvc.perform(MockMvcRequestBuilders.put("/app/order")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(order))
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.is(true)));
    }

    @Test
    public void deleteOrder() throws Exception {
        mvc.perform(MockMvcRequestBuilders.delete("/app/order/0ccf443c-c7d5-4c14-90e4-3133283a4ed7"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.is(true)));
    }
}