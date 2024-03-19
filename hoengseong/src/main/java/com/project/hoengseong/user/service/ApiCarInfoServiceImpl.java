package com.project.hoengseong.user.service;

import java.util.List;

import com.project.hoengseong.user.dao.ApiCarInfoMapper;
import com.project.hoengseong.user.model.CarInfoDTO;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import com.project.hoengseong.util.*;

@Service
@RequiredArgsConstructor
public class ApiCarInfoServiceImpl implements ApiCarInfoService {
	
    private final ApiCarInfoMapper apiCarInfoMapper;
	//@Autowired
    //ApiCarInfoMapper apiCarInfoMapper;
	
    @Override
    public List<CarInfoDTO> getCarList() {
    	return apiCarInfoMapper.getCarList();
    }
    
    @Override
    public CarInfoDTO getCarByInfo(String id) {
        return apiCarInfoMapper.getCarByInfo(id);
    }
    
    @Override
    public int carInsert(CarInfoDTO carInfoDTO) {
    	return apiCarInfoMapper.carInsert(carInfoDTO);
    }
    
    @Override
    public int carUpdate(CarInfoDTO carInfoDTO) {
    	return apiCarInfoMapper.carUpdate(carInfoDTO);
    }
    
    @Override
    public int carUpdatePut(CarInfoDTO carInfoDTO) {
        return apiCarInfoMapper.carUpdatePut(carInfoDTO);
    }
    
    @Override
    public String carDelete(String id) {    	
    	String resultCode = "400";
        String resultMsg = "Fail";
        
    	int row = apiCarInfoMapper.carDelete(id);
    	if(row > 0) {
    		resultCode = "200";
    		resultMsg = "Success";
    	}
        return CmmUtil.getRsObject(resultCode, resultMsg).toString();
    }
    
}
