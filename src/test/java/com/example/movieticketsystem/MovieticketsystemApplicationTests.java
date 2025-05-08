package com.example.movieticketsystem;

import com.example.movieticketsystem.repository.SeatRepository;
import com.example.movieticketsystem.repository.ShowTimeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
class MovieticketsystemApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private SeatRepository seatRepository;

	@Autowired
	private ShowTimeRepository showTimeRepository;

	@Test
	public void testConcurrentBooking() throws InterruptedException {
		// Giả lập việc booking đồng thời
		// Số lượng lỗi mong đợi (chỉ 1 lỗi trong tình huống này)
		int expectedErrorCount = 1;

		// Gọi phương thức để thực hiện các booking đồng thời
		int actualBookingErrors = simulateConcurrentBookings();

		// Kiểm tra số lượng lỗi
		assertEquals(expectedErrorCount, actualBookingErrors);
	}

	// Phương thức giả lập booking đồng thời
	private int simulateConcurrentBookings() throws InterruptedException {
		// Sử dụng 2 thread để mô phỏng việc đặt vé đồng thời
		Thread thread1 = new Thread(this::simulateBooking);
		Thread thread2 = new Thread(this::simulateBooking);

		thread1.start();
		thread2.start();

		// Chờ cho đến khi cả 2 thread hoàn thành
		thread1.join();
		thread2.join();

		// Giả sử chúng ta sẽ trả về số lượng lỗi (số booking không thành công)
		// Trong thực tế, bạn sẽ cần phải xác định cách thức kiểm tra lỗi
		return getBookingErrorCount(); // Giả sử phương thức này trả về số lỗi
	}

	// Phương thức giả lập một booking (để mô phỏng đồng thời)
	private void simulateBooking() {
		// Logic booking (thực hiện đặt vé)
		// Nếu có lỗi xảy ra, bạn sẽ đánh dấu hoặc lưu trữ lỗi vào một biến toàn cục
		try {
			// Ví dụ, kiểm tra nếu vé đã được đặt thì báo lỗi
			bookTicket();
		} catch (Exception e) {
			// Nếu có lỗi, lưu vào danh sách lỗi
			addBookingError(e);
		}
	}

	// Phương thức giả lập đặt vé
	private void bookTicket() throws Exception {
		// Logic để kiểm tra và thực hiện đặt vé
		// Giả sử nếu vé đã được đặt, sẽ ném ra lỗi
		throw new Exception("Booking Conflict");  // Lỗi do trùng lặp
	}

	// Biến lưu trữ lỗi trong quá trình booking
	private int bookingErrorCount = 0;

	// Phương thức thêm lỗi vào biến toàn cục
	private synchronized void addBookingError(Exception e) {
		// Tăng biến lỗi
		bookingErrorCount++;
	}

	// Phương thức trả về số lượng lỗi đã được ghi nhận
	private int getBookingErrorCount() {
		return bookingErrorCount;
	}

}
