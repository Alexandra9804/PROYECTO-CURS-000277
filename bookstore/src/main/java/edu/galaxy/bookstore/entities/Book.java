package edu.galaxy.bookstore.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "Book")
@Table(name = "tbl_book")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Book {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private Long id;
	private String title;
	private String author;
	private String isbn;
	private String publisher;
	private Long pages;
	private String genre;
	private Double price;
	private Long stock;
	private String image;
	
	@Column(nullable = false, columnDefinition = "TINYINT(1) DEFAULT 1")
	private boolean state;

}
