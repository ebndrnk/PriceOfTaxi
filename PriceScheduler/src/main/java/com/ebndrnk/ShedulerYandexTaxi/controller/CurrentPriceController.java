package com.ebndrnk.ShedulerYandexTaxi.controller;

import com.ebndrnk.ShedulerYandexTaxi.service.TaxiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/current-price")
public class CurrentPriceController {
    private final TaxiService taxiService;

    @GetMapping
    public String getCurrentPrice(Model model){
        String price = taxiService.getActualPrice();
        log.info("Some people send request to /current-price");


        model.addAttribute("currentPrice", price == null?"Just a second":price);
        return "price";
    }
}
