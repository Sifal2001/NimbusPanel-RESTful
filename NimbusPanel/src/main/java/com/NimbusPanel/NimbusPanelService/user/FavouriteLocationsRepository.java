package com.NimbusPanel.NimbusPanelService.user;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FavouriteLocationsRepository extends JpaRepository <FavouriteLocations, Long>{
	List<FavouriteLocations> findByUserId(Long userId);
}
