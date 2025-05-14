package com.NimbusPanel.NimbusPanelService.user;


import org.hibernate.tool.schema.extract.internal.SequenceNameExtractorImpl;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;


@Entity
@Table(name = "users")
public class User {
	@Id
	@SequenceGenerator(
			name = "user_sequence",
			sequenceName = "user_sequence",
			allocationSize = 1
			)
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "user_sequence"
			)
	private Long id;
	private String name;
	
	public User() {
	}
	
	public User(Long id, String name) {
		this.id = id;
		this.name = name;
	}

	public User(String name) {
		this.name = name;
	}

	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "_User [id=" + id + ", name=" + name + "]";
	}
}
