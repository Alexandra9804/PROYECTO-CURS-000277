package edu.galaxy.bookstore.services;


import java.util.List;

import org.springframework.stereotype.Service;

import edu.galaxy.bookstore.entities.Book;
import edu.galaxy.bookstore.repositories.BookRepository;

@Service
public class BookServiceImpl implements BookService {

	private final BookRepository bookRepository;
	
	public BookServiceImpl(BookRepository bookRepository)
	{
		this.bookRepository = bookRepository;
	}

	@Override
	public void saveAll(List<Book> books) {
		 bookRepository.saveAll(books);
	}

	
	

}
