package com.project.hoengseong.user.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "운행차량 DTO")
public class CarInfoDTO {
	@NotNull(message = "id cannot be null")
    @Size(min = 3, message = "id not be less than three characters")
    @Schema(description = "차량 아이디", nullable = false, example = "A00")
	private String id;
	
	@NotNull(message = "carNumber cannot be null")
    @Size(min = 4, message = "carNumber not be less than four characters")
    @Schema(description = "차량번호", nullable = false, example = "123가1111")
    private String carNumber;
	
	@NotNull(message = "breakYn cannot be null")
    @Schema(description = "고장여부", nullable = false, example = "Y/N")
    private String breakYn;
}
