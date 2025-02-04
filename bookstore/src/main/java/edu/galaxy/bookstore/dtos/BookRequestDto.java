package edu.galaxy.bookstore.dtos;

public record BookRequestDto(
		String title,
		String author,
		Long isbn,
		String publisher,
		Long pages,
		String genre,
		Double price,
		int stock,
		String image
		) {

}
