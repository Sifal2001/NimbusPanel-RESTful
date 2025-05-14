package com.NimbusPanel.NimbusPanelService.CoordResponse;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name="coord_response")
public class CoordResponse {
	@Id
	@SequenceGenerator(
			name = "location_sequence",
			sequenceName = "location_sequence",
			allocationSize = 1
			)
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "location_sequence"
			)
	private Long id;
	@OneToOne(cascade = CascadeType.ALL)
	private Coord coord;
	
	public CoordResponse() {
	}
	
	public CoordResponse(Long id, Coord coord) {
		this.id = id;
		this.coord = coord;
	}
	
	public CoordResponse( Coord coord) {
		this.coord = coord;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Coord getCoord() {
		return coord;
	}

	public void setCoord(Coord coord) {
		this.coord = coord;
	}

	@Override
	public String toString() {
		return "CoordResponse [id=" + id + ", coord=" + coord + "]";
	}


	
}
