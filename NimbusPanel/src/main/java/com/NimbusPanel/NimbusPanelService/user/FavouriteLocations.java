package com.NimbusPanel.NimbusPanelService.user;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class FavouriteLocations{
	@Id
	@GeneratedValue
	private Long id;
	
	private String location;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	public FavouriteLocations() {}

	public FavouriteLocations(Long id, String location, User user) {
		super();
		this.id = id;
		this.location = location;
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "FavouriteLocations [id=" + id + ", location=" + location + ", user=" + user + "]";
	}
	
}