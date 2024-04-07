package com.ebndrnk.ShedulerYandexTaxi.apiclient;


import io.micrometer.core.annotation.Timed;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.ebndrnk.ShedulerYandexTaxi.model.Price;

@FeignClient(name = "yandexclient", url = "${yandex.url}")
public interface TaxiApiClient {
    @Timed("gettingPriceFromYandexAPI")
    @GetMapping
    Price getPrice(@RequestParam String clid, @RequestParam String apikey, @RequestParam String rll);
}
