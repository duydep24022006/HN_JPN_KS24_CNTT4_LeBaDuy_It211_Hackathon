package com.example.hackthone_it211.service.Impl;

import com.example.hackthone_it211.exception.ResourceNotFoundException;
import com.example.hackthone_it211.model.dto.request.CarPatchRequest;
import com.example.hackthone_it211.model.dto.request.CarRequest;
import com.example.hackthone_it211.model.dto.response.CarResponseDto;
import com.example.hackthone_it211.model.entity.Car;
import com.example.hackthone_it211.repository.CarRepository;
import com.example.hackthone_it211.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;

    @Override
    @Transactional
    public CarResponseDto create(CarRequest requestDTO) {
        Car newCar = Car.builder()
                .model(requestDTO.getModel())
                .brand(requestDTO.getBrand())
                .price(requestDTO.getPrice())
                .status(requestDTO.getStatus())
                .is_deleted(false)
                .build();
        return mapToResponse(carRepository.save(newCar));
    }

    @Override
    public Page<CarResponseDto> getAll(String keyword, Pageable pageable) {
        return carRepository.findByKeyword(normalizeKeyword(keyword), pageable)
                .map(this::mapToResponse);
    }

    @Override
    @Transactional
    public CarResponseDto updatePut(Long id, CarRequest requestDTO) {
        Car car = getActiveCarById(id);
        car.setModel(requestDTO.getModel());
        car.setBrand(requestDTO.getBrand());
        car.setPrice(requestDTO.getPrice());
        car.setStatus(requestDTO.getStatus());
        return mapToResponse(carRepository.save(car));
    }

    @Override
    @Transactional
    public CarResponseDto updatePatch(Long id, CarPatchRequest requestDTO) {
        Car car = getActiveCarById(id);

        if (requestDTO.getModel() != null) {
            car.setModel(requestDTO.getModel());
        }
        if (requestDTO.getBrand() != null) {
            car.setBrand(requestDTO.getBrand());
        }
        if (requestDTO.getPrice() != null) {
            car.setPrice(requestDTO.getPrice());
        }
        if (requestDTO.getStatus() != null) {
            car.setStatus(requestDTO.getStatus());
        }

        return mapToResponse(carRepository.save(car));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Car car = getActiveCarById(id);
        car.set_deleted(true);
        carRepository.save(car);
    }

    private Car getActiveCarById(Long id) {
        return carRepository.findActiveById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy xe với ID: " + id));
    }

    private String normalizeKeyword(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return null;
        }
        return keyword.trim();
    }

    private CarResponseDto mapToResponse(Car car) {
        CarResponseDto response = new CarResponseDto();
        response.setId(car.getId());
        response.setModel(car.getModel());
        response.setBrand(car.getBrand());
        response.setStatus(car.getStatus());
        response.setPrice(car.getPrice());
        return response;
    }
}
