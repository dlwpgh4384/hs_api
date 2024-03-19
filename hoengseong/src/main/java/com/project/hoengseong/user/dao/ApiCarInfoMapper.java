package com.project.hoengseong.user.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.project.hoengseong.user.model.CarInfoDTO;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ApiCarInfoMapper {
    List<CarInfoDTO> getCarList();
    
    CarInfoDTO getCarByInfo(String id);
    
    int carInsert(CarInfoDTO carInfoDTO);
    int carUpdate(CarInfoDTO carInfoDTO);
    int carUpdatePut(CarInfoDTO carInfoDTO);
    int carDelete(String id);
}