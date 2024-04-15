package com.ebndrnk.ShedulerYandexTaxi.controller;

import com.ebndrnk.ShedulerYandexTaxi.service.TaxiService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class getCurrentPriceTest {
    @Mock
    private TaxiService taxiService;

    @InjectMocks
    private CurrentPriceController currentPriceController;

    private MockMvc mockMvc;


    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(currentPriceController).build();
    }


    @Test
    public void getCurrentPrice_request_cost() throws Exception{
        when(taxiService.getActualPrice()).thenReturn("100");

        mockMvc.perform(get("/current-price"))
                .andExpect(status().isOk())
                .andExpect(view().name("price"))
                .andExpect(model().attribute("currentPrice", "100"));
    }

    @Test
    public void getCurrentPrice_request_noResponse() throws Exception {
        when(taxiService.getActualPrice()).thenReturn(null);

        mockMvc.perform(get("/current-price"))
                .andExpect(status().isOk())
                .andExpect(view().name("price"))
                .andExpect(model().attribute("currentPrice", "Just a second"));
    }
}

