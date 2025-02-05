package edu.galaxy.bookstore.dtos;

public record PageResponseDto(
	    int size,
	    Long totalElements,
	    int totalPages,
	    Long number
	) {}
