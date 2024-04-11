package com.ebndrnk.ShedulerYandexTaxi.service;

import com.ebndrnk.ShedulerYandexTaxi.apiclient.TaxiApiClient;
import com.ebndrnk.ShedulerYandexTaxi.model.Coordinate;
import com.ebndrnk.ShedulerYandexTaxi.model.MomentPrice;
import com.ebndrnk.ShedulerYandexTaxi.model.Price;
import com.ebndrnk.ShedulerYandexTaxi.properies.YandexProperties;
import com.ebndrnk.ShedulerYandexTaxi.repository.PriceRepository;
import com.ebndrnk.ShedulerYandexTaxi.util.exception.OptionsEmptyException;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@Slf4j
public class TaxiService {
    private final TaxiApiClient taxiApiClient;
    private final PriceRepository priceRepository;
    private final YandexProperties yandexProperties;
    private AtomicInteger price;


    @Autowired
    public TaxiService(TaxiApiClient taxiApiClient, PriceRepository priceRepository, MeterRegistry meterRegistry, YandexProperties yandexProperties) {
        this.taxiApiClient = taxiApiClient;
        this.priceRepository = priceRepository;
        this.yandexProperties = yandexProperties;
        price = new AtomicInteger();
        meterRegistry.gauge("price", price);
    }

    @Transactional
    public void getPrice(Coordinate startPoint, Coordinate endPoint) {
        String rll = startPoint.toString() + "~" + endPoint.toString();
        String clid = yandexProperties.getClid();
        String apiKey = yandexProperties.getApiKey();

        Price currentPrice = taxiApiClient.getPrice(clid, apiKey, rll);

        if(currentPrice.getOptions().isEmpty()){
            log.error("Options are empty!");
            throw new OptionsEmptyException();
        }

        double priceDouble = currentPrice.getOptions().get(0).getPrice();

        price.set((int) priceDouble);

        MomentPrice momentPrice = new MomentPrice(
                LocalDateTime.now(ZoneId.of("Europe/Moscow")),
                priceDouble
        );

        priceRepository.save(momentPrice);
        log.info("Saveing to db momentPrice!: " + momentPrice);
    }

    public List<MomentPrice> getAllPrice() {
        log.info("query to repository");
       return priceRepository.findAll();
    }
}
