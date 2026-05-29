package com.example.hackthone_it211.model.dto.response;

import com.example.hackthone_it211.model.entity.CarStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CarResponseDto {

    private Long id;
    private String model;
    private String brand;
    private Double price;
    private CarStatus status;
}
