INSERT INTO movie (title, genre, description, ticket_price, duration_minutes, poster_url)
VALUES
    ('Inception', 'Sci-fi', 'A mind-bending thriller by Christopher Nolan.', 120000.0, 148, '/images/posterMovie1.jpg'),
    ('Titanic', 'Romance', 'A tragic love story on the ill-fated ship.', 90000.0, 195, '/images/posterMovie2.png'),
    ('Avengers: Endgame', 'Action', 'The epic conclusion to the Infinity Saga.', 150000.0, 181, '/images/posterMovie1.jpg'),
    ('Interstellar', 'Sci-fi', 'Exploring space and time to save humanity.', 130000.0, 169, '/images/posterMovie3.jpg'),
    ('The Dark Knight', 'Action', 'Batman faces the Joker in Gotham City.', 110000.0, 152, '/images/posterMovie2.png'),
    ('The Matrix', 'Sci-fi', 'A hacker learns the truth about the simulated world.', 95000.0, 136, '/images/posterMovie3.jpg'),
    ('Jurassic Park', 'Adventure', 'Dinosaurs brought back to life in a theme park.', 100000.0, 127, '/images/posterMovie1.jpg'),
    ('The Godfather', 'Crime', 'The powerful story of the Corleone mafia family.', 120000.0, 175, '/images/posterMovie3.jpg');


INSERT INTO show_time (start_time, end_time, total_seats, movie_id) VALUES
                                                                        ('2025-05-06 09:00:00', '2025-05-06 11:00:00', 80, 1),
                                                                        ('2025-05-06 14:00:00', '2025-05-06 16:00:00', 90, 1);

-- Suất chiếu 1
INSERT INTO seat (seat_number, is_available, seat_type, show_time_id) VALUES
                                                                          ('A1', true, 'SINGLE', 1), ('A2', true, 'SINGLE', 1), ('A3', false, 'SINGLE', 1), ('A4', true, 'SINGLE', 1), ('A5', true, 'SINGLE', 1),
                                                                          ('A6', true, 'SINGLE', 1), ('A7', true, 'SINGLE', 1), ('A8', true, 'SINGLE', 1), ('A9', true, 'SINGLE', 1), ('A10', false, 'SINGLE', 1),
                                                                          ('B1', true, 'SINGLE', 1), ('B2', true, 'SINGLE', 1), ('B3', true, 'SINGLE', 1), ('B4', true, 'SINGLE', 1), ('B5', true, 'SINGLE', 1),
                                                                          ('B6', true, 'SINGLE', 1), ('B7', true, 'SINGLE', 1), ('B8', false, 'SINGLE', 1), ('B9', true, 'SINGLE', 1), ('B10', true, 'SINGLE', 1),
                                                                          ('C1', true, 'SINGLE', 1), ('C2', true, 'SINGLE', 1), ('C3', true, 'SINGLE', 1), ('C4', true, 'SINGLE', 1), ('C5', true, 'SINGLE', 1),
                                                                          ('C6', true, 'SINGLE', 1), ('C7', true, 'SINGLE', 1), ('C8', true, 'SINGLE', 1), ('C9', true, 'SINGLE', 1), ('C10', true, 'SINGLE', 1),
                                                                          ('D1', true, 'SINGLE', 1), ('D2', false, 'SINGLE', 1), ('D3', true, 'SINGLE', 1), ('D4', true, 'SINGLE', 1), ('D5', true, 'SINGLE', 1),
                                                                          ('D6', true, 'SINGLE', 1), ('D7', true, 'SINGLE', 1), ('D8', true, 'SINGLE', 1), ('D9', true, 'SINGLE', 1), ('D10', true, 'SINGLE', 1),
                                                                          ('E1', true, 'DOUBLE', 1), ('E2', true, 'DOUBLE', 1), ('E3', true, 'DOUBLE', 1), ('E4', false, 'DOUBLE', 1), ('E5', true, 'DOUBLE', 1);

-- Suất chiếu 2
INSERT INTO seat (seat_number, is_available, seat_type, show_time_id) VALUES
                                                                          ('A1', true, 'SINGLE', 2), ('A2', true, 'SINGLE', 2), ('A3', false, 'SINGLE', 2), ('A4', true, 'SINGLE', 2), ('A5', true, 'SINGLE', 2),
                                                                          ('A6', true, 'SINGLE', 2), ('A7', true, 'SINGLE', 2), ('A8', true, 'SINGLE', 2), ('A9', true, 'SINGLE', 2), ('A10', false, 'SINGLE', 2),
                                                                          ('B1', true, 'SINGLE', 2), ('B2', true, 'SINGLE', 2), ('B3', true, 'SINGLE', 2), ('B4', true, 'SINGLE', 2), ('B5', true, 'SINGLE', 2),
                                                                          ('B6', true, 'SINGLE', 2), ('B7', true, 'SINGLE', 2), ('B8', false, 'SINGLE', 2), ('B9', true, 'SINGLE', 2), ('B10', true, 'SINGLE', 2),
                                                                          ('C1', true, 'SINGLE', 2), ('C2', true, 'SINGLE', 2), ('C3', true, 'SINGLE', 2), ('C4', true, 'SINGLE', 2), ('C5', true, 'SINGLE', 2),
                                                                          ('C6', true, 'SINGLE', 2), ('C7', true, 'SINGLE', 2), ('C8', true, 'SINGLE', 2), ('C9', true, 'SINGLE', 2), ('C10', true, 'SINGLE', 2),
                                                                          ('D1', true, 'SINGLE', 2), ('D2', false, 'SINGLE', 2), ('D3', true, 'SINGLE', 2), ('D4', true, 'SINGLE', 2), ('D5', true, 'SINGLE', 2),
                                                                          ('D6', true, 'SINGLE', 2), ('D7', true, 'SINGLE', 2), ('D8', true, 'SINGLE', 2), ('D9', true, 'SINGLE', 2), ('D10', true, 'SINGLE', 2),
                                                                          ('E1', true, 'DOUBLE', 2), ('E2', true, 'DOUBLE', 2), ('E3', true, 'DOUBLE', 2), ('E4', false, 'DOUBLE', 2), ('E5', true, 'DOUBLE', 2);

-- Tạo ticket mới (cho suất chiếu 1)
INSERT INTO ticket (customer_name, booking_time, payment_status, total_price, show_time_id)
VALUES ('Nguyen Van A', '2025-05-04T10:00:00', 'PAID', 120000.0, 1);
-- Gán các ghế đã được đặt (ví dụ seat id = 3, tức là 'A3')
-- Giả sử ticket vừa chèn có id = 1 (nếu tự tăng bắt đầu từ 1)
UPDATE seat SET ticket_id = 1, is_available = false WHERE id = 3;

