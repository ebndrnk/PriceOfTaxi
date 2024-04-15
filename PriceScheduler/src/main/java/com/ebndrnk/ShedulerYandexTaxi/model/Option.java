package com.ebndrnk.ShedulerYandexTaxi.model;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Option {
    public double price;
    public double min_price;
    public double waiting_time;
    public String class_name;
    public String price_text;
}
