package com.ebndrnk.ShedulerYandexTaxi.controller;

import com.ebndrnk.ShedulerYandexTaxi.model.MomentPrice;
import com.ebndrnk.ShedulerYandexTaxi.service.TaxiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/prices")
@Slf4j
public class AllPricesController {
    private final TaxiService taxiService;

    @GetMapping
    public List<MomentPrice> findAll(){
        log.info("Some people send request to /prices!");
        return taxiService.getAllPrice();
    }
}
