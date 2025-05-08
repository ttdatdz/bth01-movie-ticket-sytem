package com.example.movieticketsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.converter.json.GsonBuilderUtils;

@SpringBootApplication
public class MovieticketsystemApplication {

	public static void main(String[] args) {

		SpringApplication.run(MovieticketsystemApplication.class, args);

		System.out.println("Mở database tại: http://localhost:8080/h2-console \n" +
				"Nhập thông tin kết nối:\n" +
				"\n" +
				"JDBC URL: jdbc:h2:mem:movieticketsystem\n" +
				"\n" +
				"User Name: sa\n" +
				"\n" +
				"Password: (để trống)");
		System.out.println("Trang chủ tại: http://localhost:8080");
	}

}
