package com.example.movieticketsystem.service;

import com.example.movieticketsystem.model.Seat;
import com.example.movieticketsystem.model.ShowTime;
import com.example.movieticketsystem.model.Ticket;
import com.example.movieticketsystem.repository.SeatRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

@Service
public class SeatService {
    public final SeatRepository seatRepository;
    /*ReentrantLock dùng để khóa luồng, đảm bảo rằng chỉ có một luồng được thực thi đoạn mã được bảo vệ
     (giữa lock() và unlock()) tại một thời điểm.*/
    private final ReentrantLock lock = new ReentrantLock();
    public SeatService(SeatRepository seatRepository) {
        this.seatRepository = seatRepository;
    }
    @Transactional
    public void createSeatsForShowTime(ShowTime showTime, int totalSeats){
        for (int i = 1; i <= totalSeats; i++) {
            Seat seat = new Seat();
            seat.setSeatNumber("A" + i);
            seat.setShowTime(showTime);
            seat.setAvailable(true);
            seatRepository.save(seat);
        }
    }
    public List<Seat> getSeats(Long showTimeId){
        return seatRepository.findByShowTimeId(showTimeId);
    }
    public List<Seat> getAvailableSeats(Long showTimeId){
        return seatRepository.findByShowTimeIdAndIsAvailable(showTimeId,true);
    }

//    @Transactional
//    public boolean reserveSeat(Long seatId, Ticket ticket){
//        lock.lock();
//        try{
//            Seat seat = seatRepository.findById(seatId).orElse(null);
//            if(seat != null && seat.isAvailable()){
//                seat.setAvailable(false);
//                seat.setTicket(ticket);
//                seatRepository.save(seat);
//                return true;
//            }
//            return false;
//        }
//        finally {
//            lock.unlock();
//        }
//    }
@Transactional
public boolean reserveSeat(Long seatId, Ticket ticket) {
    lock.lock();
    try {
        Seat seat = seatRepository.findById(seatId).orElse(null);
        if (seat != null && seat.isAvailable()) {
            seat.setAvailable(false);
            seat.setTicket(ticket);
            seatRepository.save(seat);
            return true;
        }
        return false;
    } finally {
        lock.unlock();
    }
}

    @Transactional
    public boolean cancelSeat(Long seatId){
        lock.lock();
        try{
            Seat seat = seatRepository.findById(seatId).orElse(null);
            if(seat != null && seat.isAvailable()==false){

                seat.setAvailable(true); // Đặt lại ghế là có sẵn
                seat.setTicket(null); // Xóa liên kết với vé
                seatRepository.save(seat); // Lưu lại ghế
                return true;
            }
            return false;
        }
        finally {
            lock.unlock();
        }

    }
}
