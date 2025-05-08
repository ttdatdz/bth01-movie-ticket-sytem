package com.example.movieticketsystem.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class ShowTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private int totalSeats;

//    @ManyToOne dùng để thể hiện Quan hệ này là nhiều - một.Tức là nhiều ShowTime có thể liên kết với một Movie.
    @ManyToOne
    @JoinColumn(name = "movie_id")  //@JoinColumn dùng để nói cho JPA biết khi  lưu thông tin của một ShowTime, hãy tạo cột movie_id để lưu khóa ngoại
    private Movie movie;

//    mappedBy để chi ra rằng bên class seat giữ khóa ngoại. Cái trường showTime trong class seats giữ khóa ngoại
    @OneToMany(mappedBy = "showTime", cascade = CascadeType.ALL)
    private List<Seat> seats;

    public ShowTime(Long id, LocalDateTime startTime, LocalDateTime endTime, int totalSeats, Movie movie, List<Seat> seats) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.totalSeats = totalSeats;
        this.movie = movie;
        this.seats = seats;
    }

    public ShowTime() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public void setTotalSeats(int totalSeats) {
        this.totalSeats = totalSeats;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }
}