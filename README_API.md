# HackThone_IT211 - Car API

Dự án đã được hoàn thiện theo mẫu `Thi_Thu_WebService_full_ApiDataResponse`, nhưng vẫn giữ đối tượng chính là `Car`.

## Endpoint chính

Base URL: `/api/v1/cars`

### 1. Thêm xe
`POST /api/v1/cars`

```json
{
  "model": "Civic",
  "brand": "Honda",
  "price": 750000000,
  "status": "AVAILABLE"
}
```

### 2. Lấy danh sách + tìm kiếm + phân trang
`GET /api/v1/cars?keyword=honda&page=0&size=10`

### 3. Cập nhật toàn bộ
`PUT /api/v1/cars/{id}`

```json
{
  "model": "Camry",
  "brand": "Toyota",
  "price": 1200000000,
  "status": "AVAILABLE"
}
```

### 4. Cập nhật một phần
`PATCH /api/v1/cars/{id}`

```json
{
  "price": 1100000000
}
```

### 5. Xóa mềm
`DELETE /api/v1/cars/{id}`

## Response chuẩn
Tất cả API đều trả theo `ApiDataResponse`:

```json
{
  "success": true,
  "message": "...",
  "data": {},
  "statusCode": 200,
  "status": "OK"
}
```

## Lưu ý cấu hình database
Mở `src/main/resources/application.properties` và sửa:

```properties
spring.datasource.username=root
spring.datasource.password=mat_khau_mysql_cua_ban
```
