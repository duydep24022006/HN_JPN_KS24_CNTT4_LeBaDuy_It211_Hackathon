package com.example.hackthone_it211.repository;

import com.example.hackthone_it211.model.entity.Car;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car,Long> {
    @Query("SELECT m FROM Car m WHERE m.is_deleted = false AND " +
            "(:keyword IS NULL OR LOWER(m.model) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(m.brand) LIKE LOWER(CONCAT('%', :keyword, '%')))")
    Page<Car> findByKeyword(@Param("keyword") String keyword, Pageable pageable);

    @Query("SELECT m FROM Car m WHERE m.id = :id AND m.is_deleted = false")
    java.util.Optional<Car> findActiveById(@Param("id") Long id);
}
