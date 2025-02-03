package edu.galaxy.bookstore.repositories;



import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.galaxy.bookstore.entities.Book;

public interface BookRepository extends JpaRepository<Book, Long>{

	List<Book> findByState(Boolean state);
	List<Book> findByStateAndTitleLike(Boolean state, String title);
	Optional<Book> findByStateAndId(Boolean state, Long id);
}
