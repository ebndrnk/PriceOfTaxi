package com.ebndrnk.ShedulerYandexTaxi.Sheduler;

import com.ebndrnk.ShedulerYandexTaxi.model.Coordinate;
import com.ebndrnk.ShedulerYandexTaxi.properies.CoordinateProperties;
import com.ebndrnk.ShedulerYandexTaxi.service.TaxiService;
import io.micrometer.core.annotation.Timed;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class YandexScheduler {
    private final CoordinateProperties coordinateProperties;
    private final TaxiService taxiService;

    private int CountOfScheduling = 0;

    @Timed("schedulerTaxi")
    @Scheduled(fixedRate = 3_000)
    public void updatePrice(){
        Coordinate startPoint = new Coordinate(coordinateProperties.getStartLongitude(),
                                                coordinateProperties.getStartLatitude());

        Coordinate endPoint = new Coordinate(coordinateProperties.getFinishLongitude(),
                                            coordinateProperties.getFinishLatitude());

        int RELATIVE_LENGTH = coordinateProperties.getRelativeLength();
        taxiService.savePrice(startPoint, endPoint, RELATIVE_LENGTH);


        log.info("Sheduled running at " + CountOfScheduling++ + " times");
    }
}
