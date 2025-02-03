package edu.galaxy.bookstore.services;


import java.util.List;
import java.util.Optional;

import edu.galaxy.bookstore.entities.Book;

public interface BookService {
	void saveAll(List<Book> books);
	List<Book> findByStateAndTitlelike(Boolean state, String title);
	Book save(Book book);
	Optional<Book> findByStateAndId(Boolean state, Long id);
	Book update(Long id, Book book);
	void delete(Long id);
	Book updateStock(Long id, Book book);
}
