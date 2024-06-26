package com.ebndrnk.ShedulerYandexTaxi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;


@EnableFeignClients
@SpringBootApplication
@EnableScheduling
@EnableConfigurationProperties
public class SchedulerYandexTaxiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SchedulerYandexTaxiApplication.class, args);
	}

}
