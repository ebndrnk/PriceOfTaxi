package com.ebndrnk.ShedulerYandexTaxi.service;

import com.ebndrnk.ShedulerYandexTaxi.apiclient.TaxiApiClient;
import com.ebndrnk.ShedulerYandexTaxi.model.Coordinate;
import com.ebndrnk.ShedulerYandexTaxi.model.Price;
import com.ebndrnk.ShedulerYandexTaxi.properies.YandexProperties;
import com.ebndrnk.ShedulerYandexTaxi.repository.PriceRedisRepository;
import com.ebndrnk.ShedulerYandexTaxi.util.exception.OptionsEmptyException;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.atomic.AtomicInteger;

@Service
@Slf4j
public class TaxiService {
    private final TaxiApiClient taxiApiClient;
    private final PriceRedisRepository priceRedisRepository;
    private final YandexProperties yandexProperties;
    private AtomicInteger price;


    @Autowired
    public TaxiService(TaxiApiClient taxiApiClient, MeterRegistry meterRegistry, PriceRedisRepository priceRedisRepository, PriceRedisRepository priceRedisRepository1, YandexProperties yandexProperties) {
        this.taxiApiClient = taxiApiClient;
        this.priceRedisRepository = priceRedisRepository;
        this.yandexProperties = yandexProperties;
        price = new AtomicInteger();
        meterRegistry.gauge("price", price);
    }

    @Transactional
    public void savePrice(Coordinate startPoint, Coordinate endPoint, int relativeLength) {
        String rll = startPoint.toString() + "~" + endPoint.toString();
        String clid = yandexProperties.getClid();
        String apiKey = yandexProperties.getApiKey();

        Price currentPrice = taxiApiClient.getPrice(clid, apiKey, rll);

        if(currentPrice.getOptions().isEmpty()){
            log.error("Options are empty!");
            throw new OptionsEmptyException();
        }


        double priceDouble = currentPrice.getOptions().get(0).getPrice();

        price.set((int) (priceDouble/relativeLength*1000));
        priceRedisRepository.save("price", String.format("%.2f", priceDouble/relativeLength*1000));

    }

    public String getActualPrice(){
        return priceRedisRepository.getActualPrice();
    }

}
