package com.NimbusPanel.NimbusPanelService.CoordResponse;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "coord")
public class Coord {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private float lat;
	private float lon;
	
	public Coord() {
	}
	
	public Coord(Long id, float lat, float lon) {
		this.id = id;
		this.lat = lat;
		this.lon = lon;
	}
	
	public Coord( float lat, float lon) {
		this.lat = lat;
		this.lon = lon;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public float getLat() {
		return lat;
	}
	public void setLat(float lat) {
		this.lat = lat;
	}
	public float getLon() {
		return lon;
	}
	public void setLon(float lon) {
		this.lon = lon;
	}

	@Override
	public String toString() {
		return "Location [id=" + id + ", lat=" + lat + ", lon=" + lon + "]";
	}
}
