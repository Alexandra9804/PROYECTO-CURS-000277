package edu.galaxy.bookstore.mappers;



import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import edu.galaxy.bookstore.dtos.BookRequestDto;
import edu.galaxy.bookstore.dtos.BookResponseDto;
import edu.galaxy.bookstore.entities.Book;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BookMapper {
	BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);
	
	BookResponseDto convertToDto(Book entity);
	
	Book convertToEntity(BookRequestDto requestDto);

}
