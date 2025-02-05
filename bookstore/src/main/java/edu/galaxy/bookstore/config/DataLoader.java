package edu.galaxy.bookstore.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import edu.galaxy.bookstore.dtos.BookRequestDto;
import edu.galaxy.bookstore.entities.Book;
import edu.galaxy.bookstore.services.BookService;
import edu.galaxy.bookstore.utils.IsbnGenerator;

@Configuration
public class DataLoader {

	@Bean
	CommandLineRunner loadData(BookService bookService, IsbnGenerator isbnGenerator) {
		return args -> {
			final Integer COUNT = 100;
			
			List<BookRequestDto> bookDtos = new ArrayList<>();
			
			for(int i = 1; i<=COUNT; i++) {
				BookRequestDto bookDto = new BookRequestDto(
						"Title " + i,
						"Author " + i,
						isbnGenerator.generateIsbn(),
						"Publisher " + i,
						(long) i,
						"Genre " + i,	
						(double) i,
						i,
						"https://picsum.photos/300/500"
				);
				bookDtos.add(bookDto);
				
			}
			
			bookService.saveAll(bookDtos);
		};
	}
}
