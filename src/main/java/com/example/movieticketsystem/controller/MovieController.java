package com.example.movieticketsystem.controller;

import com.example.movieticketsystem.model.Movie;
import com.example.movieticketsystem.service.MovieService;
import com.example.movieticketsystem.service.ShowTimeService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/movies")
public class MovieController {
    private final MovieService movieService;
    private final ShowTimeService showTimeService;

    public MovieController(MovieService movieService, ShowTimeService showTimeService) {
        this.movieService = movieService;
        this.showTimeService = showTimeService;
    }
    //    Hiển thị danh sách phim
    @GetMapping
    public String showMovieList(Model model) {
        System.out.println("");
        model.addAttribute("movies", movieService.getAllMovies());
        return "movie-list";  // Trỏ đến file movie-list.html
    }
    //Hiển thị form đặt vé
    // Hiển thị form đặt vé
    @GetMapping("/{movieId}/book")
    public String showBookingForm(@PathVariable Long movieId, Model model) {
        model.addAttribute("movie", movieService.getMovieById(movieId));
        model.addAttribute("showTimes", showTimeService.getShowTimesByMovie(movieId));
        return "booking-form";
    }
//    @GetMapping("/{id}")
//    public ResponseEntity<Movie> getMovieById(@PathVariable Long id) { //ResponseEntity là 1 object đại diện cho HTTP response bao gồm : Body,Status code,Headers
//        Movie movie = movieService.getMovieById(id);
//        return movie != null ? ResponseEntity.ok(movie) : ResponseEntity.notFound().build();
//        //ResponseEntity.ok(movie) trả về 200 OK + dữ liệu movie
//       // ResponseEntity.notFound(), Trả về 404 Not Found
//        //.build() kết thúc việc cấu hình builder và trả về một ResponseEntity hoàn chỉnh => ResponseEntity.notFound().build() trả về HTTP response k có body,header, status là 404
//    }

    @PostMapping
    public Movie addMovie(@RequestBody Movie movie) {
        return movieService.addMovie(movie);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Movie> updateMovie(@PathVariable Long id, @RequestBody Movie movieDetails) {
        Movie updatedMovie = movieService.updateMovie(id, movieDetails);
        return updatedMovie != null ? ResponseEntity.ok(updatedMovie) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable Long id) {
        movieService.deleteMovie(id);
        return ResponseEntity.noContent().build();
        //ResponseEntity.noContent().build() Trả về 204 No Content (ví dụ khi xóa thành công)
    }
}
