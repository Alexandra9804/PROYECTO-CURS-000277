package edu.galaxy.bookstore.services;


import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import edu.galaxy.bookstore.dtos.BookRequestDto;
import edu.galaxy.bookstore.dtos.BookResponseDto;
import edu.galaxy.bookstore.dtos.PaginationResponseDto;
import edu.galaxy.bookstore.entities.Book;

public interface BookService {
	void saveAll(List<BookRequestDto> books);
	List<BookResponseDto> findByStateAndTitlelike(Boolean state, String title);
	PaginationResponseDto<List<BookResponseDto>> findByStateAndTitlelike(Boolean state, String title, Pageable pageable);
	BookResponseDto save(BookRequestDto bookRequestDto);
	Optional<BookResponseDto> findByStateAndId(Boolean state, Long id);
	BookResponseDto update(Long id, BookRequestDto bookRequestDto);
	void delete(Long id);
	BookResponseDto updateStock(Long id, BookRequestDto bookRequestDto);
}
