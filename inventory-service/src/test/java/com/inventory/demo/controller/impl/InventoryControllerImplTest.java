package com.inventory.demo.controller.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.inventory.demo.entity.Item;
import com.inventory.demo.service.InventoryService;
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

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = InventoryControllerImpl.class)
class InventoryControllerImplTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private InventoryService inventoryService;

    @Test
    @SneakyThrows(Exception.class)
    void getItem_pathVariableNameNotPresent() {
        mockMvc.perform(get("/item/{name}", "test"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("null"));
    }

    @Test
    @SneakyThrows(Exception.class)
    void getItem_pathVariableNamePresent() {
        when(inventoryService.getItemByName(any())).thenReturn(Optional.of(Item.builder()
                .id(1L)
                .name("item1")
                .price(100.0f)
                .build()));

        mockMvc.perform(get("/item/{name}", "test"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("{\"id\":1,\"name\":\"item1\",\"price\":100.0}"));
    }

    @Test
    @SneakyThrows(Exception.class)
    void addItem_isOk() {
        Item item = Item.builder()
                .id(1L)
                .name("Item Name")
                .price(100.0f)
                .build();

        String content = (new ObjectMapper()).writeValueAsString(item);

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/item/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(new InventoryControllerImpl(inventoryService))
                .build()
                .perform(requestBuilder);

        actualPerformResult.andExpect(status().isOk());
    }

    @Test
    @SneakyThrows(Exception.class)
    void placeOrder_isNotFound() {
        Item item = Item.builder()
                .id(1L)
                .name("Item Name")
                .price(100.0f)
                .build();

        String content = (new ObjectMapper()).writeValueAsString(item);

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/IncorrectPath")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(new InventoryControllerImpl(inventoryService))
                .build()
                .perform(requestBuilder);

        actualPerformResult.andExpect(status().isNotFound());
    }
}


