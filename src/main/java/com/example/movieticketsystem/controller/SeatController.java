package com.example.movieticketsystem.controller;

import com.example.movieticketsystem.model.Movie;
import com.example.movieticketsystem.model.Seat;
import com.example.movieticketsystem.service.MovieService;
import com.example.movieticketsystem.service.SeatService;
import com.example.movieticketsystem.service.ShowTimeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/seats")
public class SeatController {
    private final SeatService seatService;
    private final MovieService movieService;
    private final ShowTimeService showTimeService;

    public SeatController(SeatService seatService, MovieService movieService,ShowTimeService showTimeService) {
        this.seatService = seatService;
        this.movieService = movieService;
        this.showTimeService = showTimeService;
    }

    // Hiển thị tất cả ghế của showtime đó
    @GetMapping
    public String showSeats(
            @RequestParam Long showTimeId,
            Model model) {

        model.addAttribute("seats", seatService.getSeats(showTimeId));
        model.addAttribute("showTime", showTimeService.getShowTimeById(showTimeId));
        model.addAttribute("showTimeId", showTimeId);
        return "seat-selection";  // seat-selection.html
    }

}
