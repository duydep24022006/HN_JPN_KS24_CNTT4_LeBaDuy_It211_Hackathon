package com.example.hackthone_it211.model.dto.request;

import com.example.hackthone_it211.model.entity.CarStatus;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CarRequest {
    @NotBlank(message = "Không đc bỏ trống model")
    private String model;
    @NotBlank(message = "Không đc bỏ trống brand")
    private String brand;
    @NotNull(message = "Không đc bỏ trống tiền")
    @Min(value = 0,message = "Giá không đc nhỏ hơn 0")
    private Double price;
    @NotNull(message = "Không đc bỏ trống trạng thái")
    private CarStatus status;
}
