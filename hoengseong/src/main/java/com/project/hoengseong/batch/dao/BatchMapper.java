package com.project.hoengseong.batch.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.project.hoengseong.batch.model.LogDTO;

import java.util.Date;

@Mapper
@Repository
public interface BatchMapper {
    void insertLog(LogDTO logDTO);
    String idRand();
}