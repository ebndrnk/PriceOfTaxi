package com.ebndrnk.ShedulerYandexTaxi.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Price {
    public List<Option> options;
    public String currency;
    public double distance;
    public String time_text;
}
