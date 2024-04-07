package com.ebndrnk.ShedulerYandexTaxi.controller;

import com.ebndrnk.ShedulerYandexTaxi.model.MomentPrice;
import com.ebndrnk.ShedulerYandexTaxi.service.TaxiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class PriceController {
    private final TaxiService taxiService;

    @GetMapping("/prices")
    public List<MomentPrice> getAllPrice(){
        log.info("Some people send request!");
        return taxiService.getAllPrice();
    }

    @GetMapping("/current-price")
    public MomentPrice getCurrentPrice(){
        return taxiService
                .getAllPrice().get(getAllPrice().size()-1);
    }
}
