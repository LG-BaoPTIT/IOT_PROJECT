package com.example.spring.repository;

import com.example.spring.entity.LightLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LightLogRepository extends JpaRepository<LightLog, Long> {
    // Các phương thức truy vấn sẽ được thêm vào đây nếu cần


    @Override
    <S extends LightLog> S save(S entity);
}
