package com.example.hackthone_it211.model.dto.request;

import com.example.hackthone_it211.model.entity.CarStatus;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarPatchRequest {

    @Pattern(regexp = ".*\\S.*", message = "Model không được để trống")
    private String model;

    @Pattern(regexp = ".*\\S.*", message = "Brand không được để trống")
    private String brand;

    @DecimalMin(value = "0.0", message = "Giá không được nhỏ hơn 0")
    private Double price;

    private CarStatus status;
}
