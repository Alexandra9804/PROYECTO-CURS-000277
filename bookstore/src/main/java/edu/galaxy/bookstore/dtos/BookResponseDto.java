package edu.galaxy.bookstore.dtos;

public record BookResponseDto(
		Long id,
		String title,
		String author,
		Long isbn,
		String publisher,
		Long pages,
		String genre,
		Double price,
		int stock,
		String image,
		boolean state
		) {

}
