package com.project.hoengseong.user.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.hoengseong.user.service.ApiCarInfoService;
import com.project.hoengseong.util.CmmUtil;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

import com.google.gson.Gson;
import com.project.hoengseong.user.model.CarInfoDTO;

@Tag(name = "버스정보 컨트롤러", description = "버스차량 관련 API입니다.")
@RestController
@RequiredArgsConstructor
public class ApiCarInfoCtrl {

	private final ApiCarInfoService apiCarInfoService;
	
	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	Gson gson;
	
	@Operation(summary = "Rest Api Test", description = "설정 테스트 API 정보입니다.")
	@GetMapping("/test")
    public String test() {
        return "api test default";
    }

	@Operation(summary = "버스 운행 차량정보 조회", description = "전체 버스 차량정보 조회 API 입니다.")
	@ApiResponses({
        @ApiResponse(responseCode = "200", description = "차량정보 조회 성공"),
        @ApiResponse(responseCode = "400", description = "차량정보 조회했으나 차량없음"),
        @ApiResponse(responseCode = "401", description = "차량정보 조회 오류")
	})
  	@GetMapping("/api/carInfo")
    public ResponseEntity<String> getCarListCtrl() {	//차량 전체리스트 조회
    	try {
	    	List<CarInfoDTO> carList = apiCarInfoService.getCarList();
	        String responseJson = CmmUtil.generateResJson(carList, "S");
	        return ResponseEntity.status(HttpStatus.OK).headers(CmmUtil.requestHeader()).body(responseJson);
	        
    	}catch(Exception e) {
    		logger.error("exception  msg==: " , e);
    		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(CmmUtil.generateFailJson());
    	}
    }

	@Operation(summary = "특정 버스 차량 조회", description = "버스 ID로 차량정보 조회 API 입니다.")
	@GetMapping("/api/{id}")
    public ResponseEntity<String> getCarByNameCtrl(@PathVariable("id") String id) {	//차량 아이디로 차량정보 조회
    	try {
	    	CarInfoDTO carList = apiCarInfoService.getCarByInfo(id);
	        String responseJson = CmmUtil.generateResJson(carList, "S");
	        return ResponseEntity.status(HttpStatus.OK).headers(CmmUtil.requestHeader()).body(responseJson);
    	}catch(Exception e) {
    		logger.error("exception  msg==: " , e);
    		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(CmmUtil.generateFailJson());
    	}
    }
    
	
	@Operation(summary = "운행버스 등록", description = "버스 차량등록 API 입니다.")
    @PostMapping("/api/save")
    public ResponseEntity<String> insertUserCtrl(@RequestBody CarInfoDTO carInfoDTO) {	//post 차량등록
    	try {
    		int row = apiCarInfoService.carInsert(carInfoDTO);
    		String responseJson = CmmUtil.generateResJson(row, "I");
	    	return ResponseEntity.status(HttpStatus.OK).headers(CmmUtil.requestHeader()).body(responseJson);
    	}catch(Exception e) {
    		logger.error("exception  msg==: " , e);
    		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(CmmUtil.generateFailJson());
    	}
    }
    
	@Operation(summary = "운행버스 post수정", description = "POST 버스 차량 수정 API 입니다.")
    @PostMapping("/api/update")
    public ResponseEntity<String> updateUserCtrl(@RequestBody CarInfoDTO carInfoDTO) {	// 차량정보 변경 post
    	try {
    		int row = apiCarInfoService.carUpdate(carInfoDTO);
    		String responseJson = CmmUtil.generateResJson(row, "U");
	    	return ResponseEntity.status(HttpStatus.OK).headers(CmmUtil.requestHeader()).body(responseJson);
    	}catch(Exception e) {
    		logger.error("exception  msg==: " , e);
    		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(CmmUtil.generateFailJson());
    	}
    }
    
	@ApiResponses({
        @ApiResponse(responseCode = "200", description = "차량정보 수정 성공"),
        @ApiResponse(responseCode = "400", description = "차량정보 수정 내용 없음"),
        @ApiResponse(responseCode = "401", description = "차량정보 수정 오류")
	})
	@Operation(summary = "운행버스 put수정", description = "PUT 버스 차량 수정 API 입니다.")
    @PutMapping("/api/update/{id}")
    public ResponseEntity<String> updateUserPutCtrl(@PathVariable("id") String id, @RequestBody CarInfoDTO carInfoDTO) {	//ID로 차량번호 변경 put방식
    	carInfoDTO.setId(id);
    	try {
    		int row = apiCarInfoService.carUpdatePut(carInfoDTO);
    		String responseJson = CmmUtil.generateResJson(row, "U");
	    	return ResponseEntity.status(HttpStatus.OK).headers(CmmUtil.requestHeader()).body(responseJson);
    	}catch(Exception e) {
    		logger.error("exception  msg==: " , e);
    		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(CmmUtil.generateFailJson());
    	}
    }

	@Operation(summary = "운행버스 차량삭제", description = "운행차량 삭제 API 입니다.")
    @DeleteMapping("/api/delete/{id}")
    public String carDeleteCtrl(@PathVariable("id") String id) {	//차량 삭제 impl에서 메세지 적용 다른방법으로 하나만 샘플
		return apiCarInfoService.carDelete(id);
    }

}