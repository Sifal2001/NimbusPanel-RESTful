package com.NimbusPanel.NimbusPanelService.WeatherCast;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DailyWeatherRepository extends JpaRepository<DailyWeather, Long> {
}
