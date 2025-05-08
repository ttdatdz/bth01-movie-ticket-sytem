package com.example.movieticketsystem.service;

import com.example.movieticketsystem.model.Seat;
import com.example.movieticketsystem.model.ShowTime;
import com.example.movieticketsystem.model.Ticket;
import com.example.movieticketsystem.repository.SeatRepository;
import com.example.movieticketsystem.repository.TicketRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TicketService {
    private final TicketRepository ticketRepository;
    private final SeatService seatService;
    private final ShowTimeService showTimeService;
    private final SeatRepository seatRepository;

    public TicketService(TicketRepository ticketRepository, SeatService seatService, ShowTimeService showTimeService, SeatRepository seatRepository) {
        this.ticketRepository = ticketRepository;
        this.seatService = seatService;
        this.showTimeService = showTimeService;
        this.seatRepository = seatRepository;
    }
//    @Transactional
//    public Ticket bookTicket(Long showTimeId, String[] seatIdArray, String customerName) {
//        ShowTime showTime = showTimeService.getShowTimeById(showTimeId);
//
//        Ticket ticket = new Ticket();
//        ticket.setCustomerName(customerName);
//        ticket.setShowTime(showTime);
//        ticket.setBookingTime(LocalDateTime.now());
//        ticket.setPaymentStatus("PAID");
//        ticket = ticketRepository.save(ticket); // cần save trước để có ID
//
//        List<Seat> reservedSeats = new ArrayList<>();
//        double pricePerSeat = showTime.getMovie().getTicketPrice();
//        double totalPrice = 0;
//
//        for (String seatIdStr : seatIdArray) {
//            Long seatId = Long.parseLong(seatIdStr);
//            boolean reserved = seatService.reserveSeat(seatId, ticket);  // truyền ticket vào đây
//            if (!reserved) {
//                throw new RuntimeException("Ghế " + seatId + " đã được đặt");
//            }
//            Seat seat = seatRepository.findById(seatId).orElseThrow();
//            reservedSeats.add(seat);
//
//            // Tính tiền
//            if ("DOUBLE".equalsIgnoreCase(seat.getSeatType())) {
//                totalPrice += pricePerSeat * 2;
//            } else {
//                totalPrice += pricePerSeat;
//            }
//        }
//
//        ticket.setTotalPrice(totalPrice);
//        ticket.setSeats(reservedSeats);  // set danh sách ghế đã đặt
//        return ticketRepository.save(ticket); // cập nhật lại ticket lần nữa
//    }
@Transactional
public Ticket bookTicket(Long showTimeId, String[] seatIdArray, String customerName) {
    ShowTime showTime = showTimeService.getShowTimeById(showTimeId);

    Ticket ticket = new Ticket();
    ticket.setCustomerName(customerName);
    ticket.setShowTime(showTime);
    ticket.setBookingTime(LocalDateTime.now());
    ticket.setPaymentStatus("PAID");
    ticket = ticketRepository.save(ticket); // cần save trước để có ID

    List<Seat> reservedSeats = new ArrayList<>();
    double pricePerSeat = showTime.getMovie().getTicketPrice();
    double totalPrice = 0;

    for (String seatIdStr : seatIdArray) {
        Long seatId = Long.parseLong(seatIdStr);
        boolean reserved = seatService.reserveSeat(seatId, ticket);  // truyền ticket vào đây
        if (!reserved) {
            throw new RuntimeException("Ghế " + seatId + " đã được đặt hoặc không sẵn sàng");
        }
        Seat seat = seatRepository.findById(seatId).orElseThrow();
        reservedSeats.add(seat);

        // Tính tiền
        if ("DOUBLE".equalsIgnoreCase(seat.getSeatType())) {
            totalPrice += pricePerSeat * 2;
        } else {
            totalPrice += pricePerSeat;
        }
    }

    ticket.setTotalPrice(totalPrice);
    ticket.setSeats(reservedSeats);  // set danh sách ghế đã đặt
    return ticketRepository.save(ticket); // cập nhật lại ticket lần nữa
}




    @Transactional
    public boolean cancelTicket(Long ticketId) {
        Ticket ticket = ticketRepository.findById(ticketId).orElse(null);
        if (ticket != null) {
            // Duyệt qua các ghế trong vé và cập nhật lại trạng thái
            for (Seat seat : ticket.getSeats()) {
                // Chỉ cập nhật trạng thái available là true, không xóa ghế
                seat.setAvailable(true);
                seat.setTicket(null); // Hủy liên kết với vé
                seatRepository.save(seat); // Lưu thay đổi ghế
            }

            // Sau khi cập nhật ghế, xóa vé
            ticketRepository.delete(ticket);
            return true;
        }
        return false;
    }






    public Ticket getTicketById(Long id) {
        return ticketRepository.findById(id).orElse(null);
    }
    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }
}
