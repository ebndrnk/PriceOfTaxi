package com.ebndrnk.ShedulerYandexTaxi.properies;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "coordinate")
public class CoordinateProperties {
    private String startLongitude;
    private String startLatitude;
    private String finishLongitude;
    private String finishLatitude;
    private int relativeLength;
}
