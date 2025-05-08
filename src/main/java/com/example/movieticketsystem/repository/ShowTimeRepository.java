package com.example.movieticketsystem.repository;

import com.example.movieticketsystem.model.ShowTime;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShowTimeRepository extends JpaRepository<ShowTime, Long> {
    List<ShowTime> findByMovieId(Long movieId);
}
