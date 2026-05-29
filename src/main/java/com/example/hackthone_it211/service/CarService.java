package com.example.hackthone_it211.service;

import com.example.hackthone_it211.model.dto.request.CarPatchRequest;
import com.example.hackthone_it211.model.dto.request.CarRequest;
import com.example.hackthone_it211.model.dto.response.CarResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CarService {
    CarResponseDto create(CarRequest requestDTO);

    Page<CarResponseDto> getAll(String keyword, Pageable pageable);

    CarResponseDto updatePut(Long id, CarRequest requestDTO);

    CarResponseDto updatePatch(Long id, CarPatchRequest requestDTO);

    void delete(Long id);
}
