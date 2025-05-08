package com.example.movieticketsystem.controller;

import com.example.movieticketsystem.model.Seat;
import com.example.movieticketsystem.model.ShowTime;
import com.example.movieticketsystem.model.Ticket;
import com.example.movieticketsystem.service.ShowTimeService;
import com.example.movieticketsystem.service.TicketService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/tickets")
public class TicketController {
    private final TicketService ticketService;
    private final ShowTimeService showTimeService;

    public TicketController(TicketService ticketService, ShowTimeService showTimeService) {
        this.ticketService = ticketService;
        this.showTimeService = showTimeService;
    }
    @PostMapping("/cancel/{id}")
    public String cancelTicket(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        boolean cancelled = ticketService.cancelTicket(id);
        if (cancelled) {
            redirectAttributes.addFlashAttribute("message", "Đã hủy vé thành công.");
        } else {
            redirectAttributes.addFlashAttribute("error", "Không thể hủy vé.");
        }
        return "redirect:/tickets"; // Quay lại danh sách vé sau khi hủy
    }

    // Xử lý đặt vé
    @PostMapping("/book")
    public String bookTicket(
            @RequestParam Long showTimeId,
            @RequestParam String seatIds,
            @RequestParam String customerName,
            Model model) {

        // Phân tách các seatId đã chọn
        String[] seatIdArray = seatIds.split(",");

        try {
            // Gọi service để đặt vé cho tất cả các ghế đã chọn
//            Ticket ticket1 = ticketService.bookTicket(showTimeId, seatIdArray, customerName);
            Ticket ticket = ticketService.bookTicket(showTimeId, seatIdArray, customerName);

            ShowTime showTime = showTimeService.getShowTimeById(showTimeId);
            // Tính tổng tiền
            double pricePerSeat = showTime.getMovie().getTicketPrice();
            double totalPrice = 0;
            for (Seat seat : ticket.getSeats()) {
                if ("DOUBLE".equalsIgnoreCase(seat.getSeatType())) {
                    totalPrice += pricePerSeat * 2;
                } else {
                    totalPrice += pricePerSeat;
                }
            }


            model.addAttribute("ticket", ticket);
            model.addAttribute("showTime", showTime);
            model.addAttribute("totalPrice", totalPrice);

            return "booking-success";  // Trang xác nhận thành công
        } catch (Exception e) {
            model.addAttribute("error", "Đặt vé thất bại. Vui lòng thử lại.");
            return "booking-error";  // Trang thông báo lỗi
        }
    }
    @GetMapping
    public String showAllTickets(Model model) {
        List<Ticket> tickes = ticketService.getAllTickets();
        model.addAttribute("tickets", tickes);
        return "ticket-list";
    }


//    @DeleteMapping
//    public ResponseEntity<Void> cancelTicket(@PathVariable Long id) {
//        boolean cancelled = ticketService.cancelTicket(id);
//        return cancelled ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
//    }
//    @PostMapping
//    public ResponseEntity<Ticket> bookTicket(@RequestParam Long showTimeId,
//                                             @RequestParam Long seatId,
//                                             @RequestParam String customerName) {
//        Ticket ticket = ticketService.bookTicket(showTimeId, seatId, customerName);
//        return ticket != null ? ResponseEntity.ok(ticket) : ResponseEntity.badRequest().build();
//    }
//
//
//    @GetMapping("/{id}")
//    public ResponseEntity<Ticket> getTicketById(@PathVariable Long id) {
//        Ticket ticket = ticketService.getTicketById(id);
//        return ticket != null ? ResponseEntity.ok(ticket) : ResponseEntity.notFound().build();
//    }
}
