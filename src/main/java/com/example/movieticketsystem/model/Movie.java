package com.example.movieticketsystem.model;

import jakarta.persistence.*;

import java.util.List;

@Entity //	Đánh dấu class là bảng database
@Table(name = "movie")
public class Movie {
    @Id //Chỉ định một biến instance là khóa chính trong bảng
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Tự động sinh ra giá trị cho khóa chính.GenerationType.IDENTITY là tự động tăng
    private Long id;
    private String title;
    private String genre;
    private String description;
    @Column(name = "ticket_price") // Chỉ rõ tên cột
    private double ticketPrice;
    @Column(name = "duration_minutes") // Chỉ rõ tên cột
    private int durationMinutes;
    private String posterUrl;


    //OneToMany thể hiện mối quan hệ 1:n.
    // mappedBy dùng để chỉ ra rằng bên class ShowTime chứa thuộc tính Movie. Movie này trỏ đến khóa chính của bảng Movie(Movie lúc này chính là khóa ngoại)
    //cascade = CascadeType.ALL có tác dụng nếu thao tác với Movie, thì tất cả các ShowTime liên quan sẽ bị tác động theo.
    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
    private List<ShowTime> showTimes;


    public Movie() {
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }

    public Movie(Long id, String title, String genre, String description, double ticketPrice, int durationMinutes, String posterUrl, List<ShowTime> showTimes) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.description = description;
        this.ticketPrice = ticketPrice;
        this.durationMinutes = durationMinutes;
        this.posterUrl = posterUrl;
        this.showTimes = showTimes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public int getDurationMinutes() {
        return durationMinutes;
    }

    public void setDurationMinutes(int durationMinutes) {
        this.durationMinutes = durationMinutes;
    }
}
