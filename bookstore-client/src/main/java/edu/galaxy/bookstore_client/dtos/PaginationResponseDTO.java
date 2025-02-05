package edu.galaxy.bookstore_client.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaginationResponseDTO<T>{
	
	private T content;
	private PageResponseDTO page;
}
