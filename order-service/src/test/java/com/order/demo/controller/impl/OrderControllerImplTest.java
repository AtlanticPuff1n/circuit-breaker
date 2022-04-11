package com.order.demo.controller.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.order.demo.entity.Order;
import com.order.demo.service.OrderService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = OrderControllerImpl.class)
class OrderControllerImplTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderService orderService;

    @Test
    @SneakyThrows(Exception.class)
    void placeOrder_isOk() {
        Order order = Order.builder()
                .id(1L)
                .productName("productName")
                .totalPrice(100.0f)
                .build();

        String content = (new ObjectMapper()).writeValueAsString(order);

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/order")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(new OrderControllerImpl(orderService))
                .build()
                .perform(requestBuilder);

        actualPerformResult.andExpect(status().isOk());
    }

    @Test
    @SneakyThrows(Exception.class)
    void placeOrder_isNotFound() {
        Order order = Order.builder()
                .id(1L)
                .productName("productName")
                .totalPrice(100.0f)
                .build();

        String content = (new ObjectMapper()).writeValueAsString(order);

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/IncorrectPath")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(new OrderControllerImpl(orderService))
                .build()
                .perform(requestBuilder);

        actualPerformResult.andExpect(status().isNotFound());
    }
}
