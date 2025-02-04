package edu.galaxy.bookstore_client.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookRequestDTO {
	private Long id;
	private String title;
	private String author;
	private Long isbn;
	private String publisher;
	private Long pages;
	private String genre;
	private Double price;
	private int stock;
	private String image;
}
