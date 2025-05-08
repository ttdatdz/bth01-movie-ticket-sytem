package com.example.movieticketsystem.service;

import com.example.movieticketsystem.model.Movie;
import com.example.movieticketsystem.model.ShowTime;
import com.example.movieticketsystem.repository.MovieRepository;
import com.example.movieticketsystem.repository.SeatRepository;
import com.example.movieticketsystem.repository.ShowTimeRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShowTimeService {
    private final ShowTimeRepository showTimeRepository;
    private final MovieRepository movieRepository;
    private final SeatRepository seatRepository;
    private final SeatService seatService;

    public ShowTimeService(ShowTimeRepository showTimeRepository, MovieRepository movieRepository,
                           SeatRepository seatRepository, SeatService seatService){
        this.showTimeRepository = showTimeRepository;
        this.movieRepository = movieRepository;
        this.seatRepository = seatRepository;
        this.seatService = seatService;
    }
    @Transactional
    public ShowTime  addShowTime(Long movieId,ShowTime showTime, int totalSeats){
        Movie movie = movieRepository.findById(movieId).orElse(null);
        if(movie != null){
            showTime.setMovie(movie);
            ShowTime savedShowTime = showTimeRepository.save(showTime);

            //create seats for this showtime
            seatService.createSeatsForShowTime(savedShowTime, totalSeats);

            return savedShowTime;
        }
        return null;
    }
    public List<ShowTime> getShowTimesByMovie(Long movieId){
        return showTimeRepository.findByMovieId(movieId);
    }
    public ShowTime getShowTimeById(Long id){
        return showTimeRepository.findById(id).orElse(null);
    }
    public void deleteShowTime(Long id){
        showTimeRepository.deleteById(id);
    }
}
