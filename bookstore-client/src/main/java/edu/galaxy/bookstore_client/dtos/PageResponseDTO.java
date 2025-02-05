package edu.galaxy.bookstore_client.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageResponseDTO {
	
	private int size;
	private	Long totalElements;
    private int totalPages;
    private Long number;


}
