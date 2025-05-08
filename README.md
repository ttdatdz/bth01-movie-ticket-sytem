# Hướng dẫn chạy ứng dụng

**B1**: Clone project về máy

**B2**: Mở project lên

**B3**: Đợi cho IDE tự động cài đặt các cấu hình và dependencies cần thiết

**B4**: Khởi chạy dự án bằng cách ấn vào biểu tượng Run (trong IntelliJ)

**B5**: Đợi khoảng 1–3 giây cho Spring khởi tạo các controller, bean tương ứng

**B6**: Truy cập vào ứng dụng qua đường dẫn:  
http://localhost:8080

**B7**: Truy cập vào H2 Console (nếu muốn):  
http://localhost:8080/h2-console

**Thông tin kết nối:**
- JDBC URL: `jdbc:h2:mem:movieticketsystem`
- User Name: `sa`
- Password: *(để trống)*

**B8**: Để test đồng bộ giữa các luồng (1 ghế chỉ 1 người được đặt):  
Vào `TicketController.java`, tìm dòng số 50 (dòng chứa `Ticket1`) và **bỏ comment** dòng đó để bật chức năng kiểm tra.
