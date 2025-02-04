package edu.galaxy.bookstore.mappers;

import java.util.List;

import org.springframework.stereotype.Component;

import edu.galaxy.bookstore.dtos.BookResponseDto;
import edu.galaxy.bookstore.entities.Book;

@Component
public class BookCustomMapperImpl implements BookCustomMapper {

	@Override
	public BookResponseDto toDTO(Book book) {
		return new BookResponseDto(
				book.getId(), 
				book.getTitle().toUpperCase(), 
				book.getAuthor(), 
				book.getIsbn(),
				book.getPublisher(),
				book.getPages(),
				book.getGenre(),
				book.getPrice(),
				book.getStock(),
				book.getImage(), 
				book.isState()				
				);
	}

	@Override
	public List<BookResponseDto> toDTO(List<Book> books) {
		return books.stream().map(p -> toDTO(p)).toList();
	}

}
