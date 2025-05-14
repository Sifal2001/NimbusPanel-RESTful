package com.NimbusPanel.NimbusPanelService.WeatherCast;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "weather_cast")
public class WeatherCast {
	@Id
	@SequenceGenerator(
			name = "_sequence",
			sequenceName = "_sequence",
			allocationSize = 1
			)
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "_sequence"
			)
	private Long id;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "current_weather_id")
	private CurrentWeather current;
	
	@OneToMany(mappedBy = "weatherCast", cascade = CascadeType.ALL)
	private List<DailyWeather> daily;
	
//	private String description;
//	private String tempurature;
	
	public WeatherCast() {
		
	}
	
	public WeatherCast(Long id, CurrentWeather current, List<DailyWeather> daily) {
		this.id = id;
		this.current = current;
		this.daily= daily;
		
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public CurrentWeather getCurrent() {
		return current;
	}

	public void setCurrent(CurrentWeather current) {
		this.current = current;
	}

	public List<DailyWeather> getDaily() {
		return daily;
	}

	public void setDaily(List<DailyWeather> daily) {
		this.daily = daily;
	}

	@Override
	public String toString() {
		return "WeatherCast [id=" + id + ", current=" + current + ", daily=" + daily + "]";
	}
	
	
}
