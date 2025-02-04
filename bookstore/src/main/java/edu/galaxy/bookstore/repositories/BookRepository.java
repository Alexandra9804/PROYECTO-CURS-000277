package edu.galaxy.bookstore.repositories;



import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import edu.galaxy.bookstore.entities.Book;

public interface BookRepository extends JpaRepository<Book, Long>{

	List<Book> findByState(Boolean state);
	Page<Book> findByState(Boolean state, Pageable pageable);
	List<Book> findByStateAndTitleLike(Boolean state, String title);
	Page<Book> findByStateAndTitleLike(Boolean state, String title, Pageable pageable);
	Optional<Book> findByStateAndId(Boolean state, Long id);
}
