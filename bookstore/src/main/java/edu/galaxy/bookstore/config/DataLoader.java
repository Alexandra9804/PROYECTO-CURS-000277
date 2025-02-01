package edu.galaxy.bookstore.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import edu.galaxy.bookstore.entities.Book;
import edu.galaxy.bookstore.services.BookService;
import edu.galaxy.bookstore.utils.IsbnGenerator;

@Configuration
public class DataLoader {

	@Bean
	CommandLineRunner loadData(BookService bookService, IsbnGenerator isbnGenerator) {
		return args -> {
			final Integer COUNT = 10;
			
			List<Book> books = new ArrayList<>();
			
			for(int i = 1; i<=COUNT; i++) {
				Book book = new Book();
				book.setTitle("Title " + i);
				book.setAuthor("Author " + i);
				book.setIsbn(isbnGenerator.generateIsbn());
				book.setPublisher("Publisher" + i);
				book.setPages((long) i);
				book.setGenre("Genre" + i);
				book.setPrice((double) i);
				book.setStock(i);
				book.setImage("https://picsum.photos/300/200");
				book.setState(true);
				
				books.add(book);
				
			}
			
			bookService.saveAll(books);
		};
	}
}
