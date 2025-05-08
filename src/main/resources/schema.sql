CREATE TABLE movie (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       title VARCHAR(255) NOT NULL,
                       genre VARCHAR(255) NOT NULL,
                       description TEXT,
                       ticket_price DOUBLE NOT NULL,
                       duration_minutes INT NOT NULL,
                       poster_url VARCHAR(500) NOT NULL
);
CREATE TABLE show_time (
                           id BIGINT PRIMARY KEY AUTO_INCREMENT,
                           start_time TIMESTAMP,
                           end_time TIMESTAMP,
                           total_seats INT,
                           movie_id BIGINT,
                           CONSTRAINT fk_showtime_movie FOREIGN KEY (movie_id) REFERENCES movie(id)
);
CREATE TABLE ticket (
                        id BIGINT PRIMARY KEY AUTO_INCREMENT,
                        customer_name VARCHAR(255),
                        booking_time TIMESTAMP,
                        payment_status VARCHAR(50),
                        total_price DOUBLE,
                        show_time_id BIGINT,
                        CONSTRAINT fk_ticket_showtime FOREIGN KEY (show_time_id) REFERENCES show_time(id)
);
CREATE TABLE seat (
                      id BIGINT PRIMARY KEY AUTO_INCREMENT,
                      seat_number VARCHAR(10),
                      is_available BOOLEAN DEFAULT true,
                      seat_type VARCHAR(20),
                      show_time_id BIGINT,
                      ticket_id BIGINT,  -- thêm cột này để ánh xạ tới ticket
                      CONSTRAINT fk_seat_showtime FOREIGN KEY (show_time_id) REFERENCES show_time(id),
                      CONSTRAINT fk_seat_ticket FOREIGN KEY (ticket_id) REFERENCES ticket(id)
);


