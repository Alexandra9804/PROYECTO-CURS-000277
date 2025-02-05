package edu.galaxy.bookstore.services;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import edu.galaxy.bookstore.constant.RecordStateConstant;
import edu.galaxy.bookstore.dtos.BookRequestDto;
import edu.galaxy.bookstore.dtos.BookResponseDto;
import edu.galaxy.bookstore.dtos.PageResponseDto;
import edu.galaxy.bookstore.dtos.PaginationResponseDto;
import edu.galaxy.bookstore.entities.Book;
import edu.galaxy.bookstore.mappers.BookCustomMapper;
import edu.galaxy.bookstore.mappers.BookCustomMapperImpl;
import edu.galaxy.bookstore.mappers.BookMapper;
import edu.galaxy.bookstore.repositories.BookRepository;

@Service
public class BookServiceImpl implements BookService {

	private final BookRepository bookRepository;
	private final BookCustomMapper bookCustomMapper;
	
	public BookServiceImpl(BookRepository bookRepository, BookCustomMapper bookCustomMapper)
	{
		this.bookRepository = bookRepository;
		this.bookCustomMapper = bookCustomMapper;
	}

	@Override
	public void saveAll(List<BookRequestDto> books) {
		
		List<Book> booksEntity = books.stream()
				.map(BookMapper.INSTANCE::convertToEntity)
				 .peek(book -> book.setState(true)) 
				.toList();
		
		 bookRepository.saveAll(booksEntity);
		
	}

	@Override
	public List<BookResponseDto> findByStateAndTitlelike(Boolean state, String title) {
		
		Optional<String> optionalName = Optional.ofNullable(title);

		if(optionalName.isPresent()) {
			return  bookCustomMapper.toDTO(bookRepository.findByStateAndTitleLike(RecordStateConstant.ACTIVE, "%" + title.trim() + "%"));
		}else {
			return bookCustomMapper.toDTO(bookRepository.findByState(RecordStateConstant.ACTIVE));
		}
	}
	
	@Override
	public PaginationResponseDto<List<BookResponseDto>> findByStateAndTitlelike(Boolean state, String title, Pageable pageable) {
	    Optional<String> optionalName = Optional.ofNullable(title);

	    Page<Book> bookPage;

	    if (optionalName.isPresent()) {
	        bookPage = bookRepository.findByStateAndTitleLike(RecordStateConstant.ACTIVE, "%" + title.trim() + "%", pageable);
	    } else {
	        bookPage = bookRepository.findByState(RecordStateConstant.ACTIVE, pageable);
	    }


	    List<BookResponseDto> bookResponseList = bookPage.stream()
	            .map(bookCustomMapper::toDTO)
	            .toList();


	    return new PaginationResponseDto<>(
	            bookResponseList,
	            new PageResponseDto(
	                    bookPage.getSize(),
	                    bookPage.getTotalElements(),
	                    bookPage.getTotalPages(),
	                    (long) bookPage.getNumber()
	            )
	    );
	}



	@Override
	public BookResponseDto save(BookRequestDto bookRequestDto) {
		Book book = BookMapper.INSTANCE.convertToEntity(bookRequestDto);
		book.setState(true);
		book = this.bookRepository.save(book);
		
		return BookMapper.INSTANCE.convertToDto(book);
	}

	@Override
	public Optional<BookResponseDto> findByStateAndId(Boolean state, Long id) {
		Book book = bookRepository.findByStateAndId(RecordStateConstant.ACTIVE, id).orElseThrow(() -> new RuntimeException("Book not found"));
		return Optional.ofNullable(bookCustomMapper.toDTO(book));
	}

	@Override
	public BookResponseDto update(Long id, BookRequestDto bookRequestDto) {
		 return bookRepository.findByStateAndId(RecordStateConstant.ACTIVE, id)
			        .map(bookToUpdate -> {
			        	bookToUpdate.setTitle(bookRequestDto.title());
			        	bookToUpdate.setAuthor(bookRequestDto.author());
			        	bookToUpdate.setIsbn(bookRequestDto.isbn());
			        	bookToUpdate.setPublisher(bookRequestDto.publisher());
			        	bookToUpdate.setPages(bookRequestDto.pages());
			        	bookToUpdate.setGenre(bookRequestDto.genre());
			        	bookToUpdate.setPrice(bookRequestDto.price());
			        	bookToUpdate.setStock(bookRequestDto.stock());
			        	bookToUpdate.setImage(bookRequestDto.image());
			        	
			            Book updatedBook = bookRepository.save(bookToUpdate);
			            
			            return BookMapper.INSTANCE.convertToDto(updatedBook);
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
	public BookResponseDto updateStock(Long id, BookRequestDto bookRequestDto) {
		return bookRepository.findByStateAndId(RecordStateConstant.ACTIVE, id)
				.map(bookStockToUpdate -> {
					bookStockToUpdate.setStock(bookRequestDto.stock());
					Book updatedStockBook  = bookRepository.save(bookStockToUpdate);
					
					return BookMapper.INSTANCE.convertToDto(updatedStockBook);
					
				}).orElseThrow(() -> new RuntimeException("Book with id " + id + "not found"));
	}

}
