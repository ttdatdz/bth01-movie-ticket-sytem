package com.example.movieticketsystem.service;

import com.example.movieticketsystem.model.Movie;
import com.example.movieticketsystem.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {
    @Autowired
    private final MovieRepository movieRepository;
    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }
    public List<Movie> getAllMovies (){
        return movieRepository.findAll();
    }
    public Movie getMovieById(Long id){
        return movieRepository.findById(id).orElse(null); //Tìm một Movie theo id.Nếu không tìm thấy thì trả về null.
    }
    public Movie addMovie(Movie movie){
        return movieRepository.save(movie); //save() dùng để add hoặc update 1 bản ghi vào CSDL. Nếu bản ghi đó chưa có id thì nó sẽ thêm mới, còn có rồi thì sẽ update
    }
    public Movie updateMovie(Long id, Movie movieDetails){
        Movie movie = movieRepository.findById(id).orElse(null);
        if(movie != null){
            movie.setTitle(movieDetails.getTitle());
            movie.setGenre(movieDetails.getGenre());
            movie.setDescription(movieDetails.getDescription());
            movie.setTicketPrice(movieDetails.getTicketPrice());
            movie.setDurationMinutes(movieDetails.getDurationMinutes());
            return movieRepository.save(movie);
        }
        return null;
    }
    public void deleteMovie(Long id) {
        movieRepository.deleteById(id);
    }
}
