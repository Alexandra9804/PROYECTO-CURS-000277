package edu.galaxy.bookstore.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.galaxy.bookstore.constant.RecordStateConstant;
import edu.galaxy.bookstore.entities.Book;
import edu.galaxy.bookstore.services.BookService;

@RestController
@RequestMapping("/api/v1/books")
public class BookController {

	@Autowired
	private BookService bookService;
	
	@GetMapping
	public List<Book> findAllOrFilterByTitle(@RequestParam(required=false) String title){
		return bookService.findByStateAndTitlelike(RecordStateConstant.ACTIVE, title);
	}
	
	@PostMapping
	public Book save(@RequestBody Book book) {
		return bookService.save(book);
	}
	
	@GetMapping("/{id}")
	public Optional<Book> findByStateAndId(@PathVariable Long id) {
		return bookService.findByStateAndId(RecordStateConstant.ACTIVE, id);
	}
	
	@PutMapping("/{id}")
	public Book update(@PathVariable Long id, @RequestBody Book book) {
		return bookService.update(id, book);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		bookService.delete(id);
	}
	
	@PatchMapping("/{id}")
	public Book updateStock(@PathVariable Long id, @RequestBody Book book) {
		return bookService.updateStock(id, book);
	}
}
