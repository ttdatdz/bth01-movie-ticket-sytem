package com.example.movieticketsystem.repository;

import com.example.movieticketsystem.model.Seat;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SeatRepository extends JpaRepository<Seat, Long> {
    List<Seat> findByShowTimeIdAndIsAvailable(Long showTimeId, boolean isAvailable);
    List<Seat> findByShowTimeId(Long showTimeId);

//@Transactional: báo rằng đây là 1 chuỗi hành động tương tác với cơ sở dữ liệu, cần một transaction.
//@Modifying: báo rằng đây không phải là SELECT, mà là UPDATE hoặc DELETE.
//@Query(...): định nghĩa câu lệnh JPQL(Java Persistence Query Language) để cập nhật.
    @Transactional
    @Modifying
    @Query("UPDATE Seat s SET s.isAvailable = ?2 WHERE s.id = ?1")
    void updateSeatAvailability(Long seatId, boolean isAvailable);
}
