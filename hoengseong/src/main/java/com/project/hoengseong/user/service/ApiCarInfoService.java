package com.project.hoengseong.user.service;

import java.util.List;

import com.project.hoengseong.user.model.CarInfoDTO;

public interface ApiCarInfoService {
	
	public List<CarInfoDTO> getCarList();
	
	CarInfoDTO getCarByInfo(String id);
	
	int carInsert(CarInfoDTO carInfoDTO);
	int carUpdate(CarInfoDTO carInfoDTO);
	int carUpdatePut(CarInfoDTO carInfoDTO);
	String carDelete(String id);
}
