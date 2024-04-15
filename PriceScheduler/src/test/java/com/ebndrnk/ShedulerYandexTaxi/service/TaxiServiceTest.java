package com.ebndrnk.ShedulerYandexTaxi.service;

import com.ebndrnk.ShedulerYandexTaxi.model.Price;
import com.ebndrnk.ShedulerYandexTaxi.util.exception.OptionsEmptyException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest
public class TaxiServiceTest {

    @Test
    public void savePrice_EmptyOptions_OptionsAreEmptyException(){


        Price currentPrice = Price.builder()
                .options(Collections.emptyList())
                .currency("Currency")
                .distance(0)
                .time_text("Time text")
                .build();

        assertThrows(OptionsEmptyException.class, ()->{
            if(currentPrice.getOptions().isEmpty()){
                throw new OptionsEmptyException();
            }
        });


    }

}

