package com.project.hoengseong;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//batch기능 활성화 3.0이상부터는 선언안함
//@EnableBatchProcessing
@SpringBootApplication
public class HoengseongApplication {

	public static void main(String[] args) {
		SpringApplication.run(HoengseongApplication.class, args);
	}

}
