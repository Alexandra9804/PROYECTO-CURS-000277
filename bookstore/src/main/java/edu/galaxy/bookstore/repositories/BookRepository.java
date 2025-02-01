package edu.galaxy.bookstore.repositories;



import org.springframework.data.jpa.repository.JpaRepository;

import edu.galaxy.bookstore.entities.Book;

public interface BookRepository extends JpaRepository<Book, Long>{

}
