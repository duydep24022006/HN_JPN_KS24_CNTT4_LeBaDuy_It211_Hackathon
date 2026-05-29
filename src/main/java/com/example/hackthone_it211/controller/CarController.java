package com.example.hackthone_it211.controller;

import com.example.hackthone_it211.model.dto.request.CarPatchRequest;
import com.example.hackthone_it211.model.dto.request.CarRequest;
import com.example.hackthone_it211.model.dto.response.ApiDataResponse;
import com.example.hackthone_it211.model.dto.response.CarResponseDto;
import com.example.hackthone_it211.service.CarService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/cars")
@RequiredArgsConstructor
public class CarController {

    private final CarService carService;

    @PostMapping
    public ResponseEntity<ApiDataResponse<CarResponseDto>> create(
            @Valid @RequestBody CarRequest requestDTO
    ) {
        CarResponseDto response = carService.create(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiDataResponse.success("Thêm mới xe thành công", response, HttpStatus.CREATED));
    }

    @GetMapping
    public ResponseEntity<ApiDataResponse<Page<CarResponseDto>>> getAll(
            @RequestParam(value = "keyword", required = false) String keyword,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<CarResponseDto> response = carService.getAll(keyword, pageable);
        return ResponseEntity.ok(ApiDataResponse.success("Lấy danh sách xe thành công", response, HttpStatus.OK));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiDataResponse<CarResponseDto>> updatePut(
            @PathVariable Long id,
            @Valid @RequestBody CarRequest requestDTO
    ) {
        CarResponseDto response = carService.updatePut(id, requestDTO);
        return ResponseEntity.ok(ApiDataResponse.success("Cập nhật toàn bộ thông tin xe thành công", response, HttpStatus.OK));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiDataResponse<CarResponseDto>> updatePatch(
            @PathVariable Long id,
            @Valid @RequestBody CarPatchRequest requestDTO
    ) {
        CarResponseDto response = carService.updatePatch(id, requestDTO);
        return ResponseEntity.ok(ApiDataResponse.success("Cập nhật một phần thông tin xe thành công", response, HttpStatus.OK));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiDataResponse<String>> delete(@PathVariable Long id) {
        carService.delete(id);
        return ResponseEntity.ok(ApiDataResponse.success("Xóa xe thành công", "Đã xóa mềm xe có ID: " + id, HttpStatus.OK));
    }
}
