package edu.galaxy.bookstore.mappers;

import java.util.List;

import edu.galaxy.bookstore.dtos.BookResponseDto;
import edu.galaxy.bookstore.entities.Book;

public interface BookCustomMapper {

	BookResponseDto toDTO(Book book);
	List<BookResponseDto> toDTO(List<Book> books);
}
