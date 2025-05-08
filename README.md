#Để chạy ứng dụng cần làm những công việc sau:
#B1: Clone project về máy
#B2: Mở project lên
#B3: Đợi cho IDE nó cài đặt các cấu hình, dependencies cần thiết
#B4: Khởi chạy dự án bằng cách ấn vào biểu tượng Run (IntelliJ)
#B5: Đợi khoảng 1-3s cho Spring khởi tạo các controller, bean tương ứng
#B6: Truy cập vào đường dẫn http://localhost:8080 để mở ứng dụng. 
#B7: Nếu muốn truy cập vào  http://localhost:8080/h2-console để mở database kèm nhập thông tin kết nối:

JDBC URL: jdbc:h2:mem:movieticketsystem

User Name: sa

Password: (để trống)
#B8: Muốn test đồng bộ giữa các luồng. Tức 1 ghế chỉ 1 người đặt, 1 ghế không thể nào nhiều người đặt cùng lúc được thì vào TicketController xóa comment ở dòng 50 tức là dòng chứa Ticket1
