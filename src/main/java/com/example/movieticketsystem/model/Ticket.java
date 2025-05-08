package com.example.movieticketsystem.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String customerName;
    private LocalDateTime bookingTime;
    private String paymentStatus;

    private double totalPrice;

    @OneToMany(mappedBy = "ticket", cascade = CascadeType.PERSIST, orphanRemoval = false)
    private List<Seat> seats;


    @ManyToOne
    @JoinColumn(name = "show_time_id")
    private ShowTime showTime;

    public Ticket() {}

    public Ticket(String paymentStatus, List<Seat> seats, ShowTime showTime, Long id, String customerName, LocalDateTime bookingTime, double totalPrice) {
        this.paymentStatus = paymentStatus;
        this.seats = seats;
        this.showTime = showTime;
        this.id = id;
        this.customerName = customerName;
        this.bookingTime = bookingTime;
        this.totalPrice = totalPrice;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public LocalDateTime getBookingTime() {
        return bookingTime;
    }

    public void setBookingTime(LocalDateTime bookingTime) {
        this.bookingTime = bookingTime;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }

    public ShowTime getShowTime() {
        return showTime;
    }

    public void setShowTime(ShowTime showTime) {
        this.showTime = showTime;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
