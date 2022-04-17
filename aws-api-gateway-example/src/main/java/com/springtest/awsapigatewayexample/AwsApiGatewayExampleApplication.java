package com.springtest.awsapigatewayexample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@RestController
@RequestMapping("/book")
public class AwsApiGatewayExampleApplication {


	private List<Book> books = new ArrayList<>();

	@PostMapping
	public Book addBook(@RequestBody Book book) {
		books.add(book);
		return book;
	}

	@GetMapping
	public List<Book> getBooks() {
		return books;
	}



	public static void main(String[] args) {
		SpringApplication.run(AwsApiGatewayExampleApplication.class, args);
	}

}
