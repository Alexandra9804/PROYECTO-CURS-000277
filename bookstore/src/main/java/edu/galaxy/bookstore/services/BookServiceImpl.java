package edu.galaxy.bookstore.services;


import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import edu.galaxy.bookstore.constant.RecordStateConstant;
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

	@Override
	public List<Book> findByStateAndTitlelike(Boolean state, String title) {
		
		Optional<String> optionalName = Optional.ofNullable(title);

		if(optionalName.isPresent()) {
			return bookRepository.findByStateAndTitleLike(RecordStateConstant.ACTIVE, "%" + title.trim() + "%");
		}else {
			return bookRepository.findByState(RecordStateConstant.ACTIVE);
		}
	}

	@Override
	public Book save(Book book) {
		book.setState(true);
		return bookRepository.save(book);
	}

	@Override
	public Optional<Book> findByStateAndId(Boolean state, Long id) {
		Book book = bookRepository.findByStateAndId(RecordStateConstant.ACTIVE, id).orElseThrow(() -> new RuntimeException("Book not found"));
		return Optional.ofNullable(book);
	}

	@Override
	public Book update(Long id, Book book) {
		 return bookRepository.findByStateAndId(RecordStateConstant.ACTIVE, id)
			        .map(bookToUpdate -> {
			        	bookToUpdate.setTitle(book.getTitle());
			        	bookToUpdate.setAuthor(book.getAuthor());
			        	bookToUpdate.setIsbn(book.getIsbn());
			        	bookToUpdate.setPublisher(book.getPublisher());
			        	bookToUpdate.setPages(book.getPages());
			        	bookToUpdate.setGenre(book.getGenre());
			        	bookToUpdate.setPrice(book.getPrice());
			        	bookToUpdate.setStock(book.getStock());
			        	bookToUpdate.setImage(book.getImage());
			            return bookRepository.save(bookToUpdate);
			        })
			        .orElseThrow(() -> new RuntimeException("Book with id " + id + " not found"));
	}

	@Override
	public void delete(Long id) {
		Book oBook = bookRepository.findByStateAndId(RecordStateConstant.ACTIVE, id).orElseThrow(() -> new RuntimeException("Book not found")); 
		oBook.setState(false);
		bookRepository.save(oBook);
	}

	@Override
	public Book updateStock(Long id, Book book) {
		return bookRepository.findByStateAndId(RecordStateConstant.ACTIVE, id)
				.map(bookStockToUpdate -> {
					bookStockToUpdate.setStock(book.getStock());
					return bookRepository.save(bookStockToUpdate);
				}).orElseThrow(() -> new RuntimeException("Book with id " + id + "not found"));
	}

	

	
	

}
