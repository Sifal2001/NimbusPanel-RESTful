package com.NimbusPanel.NimbusPanelService.WeatherCast;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "weather_condition")
public class WeatherCondition {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    private String main;
    private String description;
    private String icon;
    
    @ManyToOne
    @JoinColumn(name = "current_weather_id")
    private CurrentWeather currentWeather;

    @ManyToOne
    @JoinColumn(name = "daily_weather_id")
    private DailyWeather dailyWeather;
    
	public WeatherCondition() {
	}
    
	public WeatherCondition(Long id, String main, String description, String icon) {
		this.id = id;
		this.main = main;
		this.description = description;
		this.icon = icon;
	}
	
	public WeatherCondition(String main, String description, String icon) {
		this.main = main;
		this.description = description;
		this.icon = icon;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getMain() {
		return main;
	}
	public void setMain(String main) {
		this.main = main;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	
	
	public CurrentWeather getCurrentWeather() {
		return currentWeather;
	}

	public void setCurrentWeather(CurrentWeather currentWeather) {
		this.currentWeather = currentWeather;
	}

	public DailyWeather getDailyWeather() {
		return dailyWeather;
	}

	public void setDailyWeather(DailyWeather dailyWeather) {
		this.dailyWeather = dailyWeather;
	}

	@Override
	public String toString() {
		return "WeatherCondition [id=" + id + ", main=" + main + ", description=" + description + ", icon=" + icon
				+ "]";
	}

}
