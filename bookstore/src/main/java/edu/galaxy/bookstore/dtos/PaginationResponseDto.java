package edu.galaxy.bookstore.dtos;

public record PaginationResponseDto<T>(
	    T content,
	    PageResponseDto page
	) {}

