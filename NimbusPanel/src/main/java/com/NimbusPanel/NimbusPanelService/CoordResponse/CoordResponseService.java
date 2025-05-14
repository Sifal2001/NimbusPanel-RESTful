package com.NimbusPanel.NimbusPanelService.CoordResponse;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CoordResponseService {
	private final CoordResponseRepository coordResponseRepository;
	
	@Autowired
	public CoordResponseService(CoordResponseRepository coordResponseRepository) {
		this.coordResponseRepository = coordResponseRepository;
	}
	
	public List<CoordResponse> getCoordResponse() {
		return coordResponseRepository.findAll();
	}
}
