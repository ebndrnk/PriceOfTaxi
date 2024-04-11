package com.ebndrnk.ShedulerYandexTaxi.repository;

import com.ebndrnk.ShedulerYandexTaxi.model.MomentPrice;
import io.micrometer.core.annotation.Timed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PriceRepository extends JpaRepository<MomentPrice, Long> {
    @Timed("gettingAllPrices")
    List<MomentPrice> findAll();
}
