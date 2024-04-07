package com.ebndrnk.ShedulerYandexTaxi.model;

import lombok.Data;
import com.ebndrnk.ShedulerYandexTaxi.model.Option;


import java.util.List;

@Data
public class Price {
    public List<Option> options;
    public String currency;
    public double distance;
    public String time_text;
}
