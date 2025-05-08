package com.example.movieticketsystem.model;

import jakarta.persistence.*;

@Entity
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String seatNumber;
    private boolean isAvailable = true;
    private String seatType; // Ví dụ: "SINGLE", "DOUBLE"

    @ManyToOne
    @JoinColumn(name = "show_time_id")
    private ShowTime showTime;

    @ManyToOne
    @JoinColumn(name = "ticket_id")
    private Ticket ticket;

    public Seat() {
    }

    public Seat(Long id, String seatNumber, boolean isAvailable, String seatType, ShowTime showTime, Ticket ticket) {
        this.id = id;
        this.seatNumber = seatNumber;
        this.isAvailable = isAvailable;
        this.seatType = seatType;
        this.showTime = showTime;
        this.ticket = ticket;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public boolean isAvailable() {
        return isAvailable;
    }
    public String getSeatType() {
        return seatType;
    }

    public void setSeatType(String seatType) {
        this.seatType = seatType;
    }
    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public ShowTime getShowTime() {
        return showTime;
    }

    public void setShowTime(ShowTime showTime) {
        this.showTime = showTime;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }


}
