package edu.galaxy.bookstore.services;


import java.util.List;

import edu.galaxy.bookstore.entities.Book;

public interface BookService {
	void saveAll(List<Book> books);
}
