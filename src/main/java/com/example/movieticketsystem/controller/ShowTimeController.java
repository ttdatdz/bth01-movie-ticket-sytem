package com.example.movieticketsystem.controller;

import com.example.movieticketsystem.model.Movie;
import com.example.movieticketsystem.model.ShowTime;
import com.example.movieticketsystem.service.MovieService;
import com.example.movieticketsystem.service.ShowTimeService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/movies/{movieId}/showtimes")
public class ShowTimeController {
    private final ShowTimeService showTimeService;
    private final MovieService movieService;

    public ShowTimeController(ShowTimeService showTimeService, MovieService movieService) {
        this.showTimeService = showTimeService;
        this.movieService = movieService;
    }
//    Model là đối tượng chứa dữ liệu bạn muốn truyền từ controller đến view.addAttribute() là phương thức dùng để thêm một thuộc tính vào model, để nó có thể được sử dụng trong view.
@GetMapping
public String getShowTimesByMovie(@PathVariable Long movieId, Model model) {
    model.addAttribute("showTimes", showTimeService.getShowTimesByMovie(movieId));

    // Nếu có movieService:
    Movie movie = movieService.getMovieById(movieId);
    model.addAttribute("movie", movie);

    return "showtimes";
}


}
