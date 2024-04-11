package com.ebndrnk.ShedulerYandexTaxi.controller;

import com.ebndrnk.ShedulerYandexTaxi.model.MomentPrice;
import com.ebndrnk.ShedulerYandexTaxi.service.TaxiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/current-price")
public class CurrentPriceController {
    private final TaxiService taxiService;

    @GetMapping
    public String getCurrentPrice(Model model){
        List<MomentPrice> listOfAllPrices = taxiService.getAllPrice();
        String price;
        log.info("Some people send request to /current-price");
        if(listOfAllPrices.isEmpty()){
            price = "Just a second!";
        }
        else {
            price = listOfAllPrices.get(listOfAllPrices.size() - 1).getPrice().toString();
        }

        model.addAttribute("currentPrice", price);
        return "price";
    }
}
