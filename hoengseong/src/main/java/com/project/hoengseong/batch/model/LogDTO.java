package com.project.hoengseong.batch.model;

import java.util.Date;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Batch Log DTO")
public class LogDTO {
	@NotNull(message = "id cannot be null")
    @Size(min = 1, message = "id not be less than three characters")
    @Schema(description = "log 아이디", nullable = false, example = "random")
	private String id;
	
	@NotNull(message = "regDt cannot be null")
    @Schema(description = "실행날짜", nullable = false, example = "Y-m-d")
    private Date regDt;
}
