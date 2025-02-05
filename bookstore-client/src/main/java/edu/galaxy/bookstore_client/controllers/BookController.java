package edu.galaxy.bookstore_client.controllers;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import edu.galaxy.bookstore_client.dtos.BookRequestDTO;
import edu.galaxy.bookstore_client.dtos.BookResponseDTO;
import edu.galaxy.bookstore_client.dtos.PageResponseDTO;
import edu.galaxy.bookstore_client.dtos.PaginationResponseDTO;

@Controller
public class BookController {
	@Value("${backend.url}")
	private String apiUrl;
	private RestTemplate restTemplate;
	
	public BookController(RestTemplateBuilder restTemplateBuilder) {
		this.restTemplate = restTemplateBuilder.rootUri("").build();
	}
	
	
	@GetMapping
	public String findAllOrFilterByTitle(@RequestParam(required = false) String title, Model model) {
		Optional<String> optionalName = Optional.ofNullable(title);
		ResponseEntity<BookResponseDTO[]> books;
		if (optionalName.isPresent()) {
			books = this.restTemplate.getForEntity(this.apiUrl + "?title=" + title, BookResponseDTO[].class);
		} else {
			books = this.restTemplate.getForEntity(this.apiUrl, BookResponseDTO[].class);
		}
		model.addAttribute("books", books.getBody());
		return "books";
	}
	
	@GetMapping("/paginado")
	public String findAllOrFilterByTitle(
	    @RequestParam(required = false) String title,
	    @RequestParam(defaultValue = "1") int pageNumber,
	    @RequestParam(defaultValue = "10") int pageSize,
	    @RequestParam(defaultValue = "id,title") String[] fields,
	    @RequestParam(defaultValue = "DESC") String order,
	    Model model
	) {

	    String url = apiUrl + "/byPage?title=" + (title != null ? title : "") 
	                + "&pageNumber=" + pageNumber
	                + "&pageSize=" + pageSize
	                + "&fields=" + String.join(",", fields)
	                + "&order=" + order;


	    ResponseEntity<PaginationResponseDTO<List<BookResponseDTO>>> response = this.restTemplate.exchange(
	        url,
	        HttpMethod.GET,
	        null,
	        new ParameterizedTypeReference<PaginationResponseDTO<List<BookResponseDTO>>>() {}
	    );

	    PaginationResponseDTO<List<BookResponseDTO>> paginationResponse = response.getBody();


	    model.addAttribute("books", paginationResponse.getContent());
	    model.addAttribute("currentPage", paginationResponse.getPage().getNumber() + 1); 
	    model.addAttribute("totalPages", paginationResponse.getPage().getTotalPages());
	    model.addAttribute("title", title); 
	    model.addAttribute("pageSize", pageSize);

	    return "books"; 
	}
	
	@GetMapping("/{id}")
	public String findById(@PathVariable Long id, Model model) {
		ResponseEntity<BookResponseDTO> book = this.restTemplate.getForEntity(this.apiUrl + '/' + id, BookResponseDTO.class);
		model.addAttribute("book", book.getBody());
		return "book-details";
	}
	
	@GetMapping("/nuevo")
	public String showFormToRegister(Model model) {
		model.addAttribute("book", new BookRequestDTO()); 
        model.addAttribute("titulo", "Registrar Libro");
        model.addAttribute("action", "/guardar");
		return "new-form";
	}
	
	@PostMapping("/guardar")
	public String save(BookRequestDTO bookRequestDTO) {
		this.restTemplate.postForEntity(this.apiUrl, bookRequestDTO, BookRequestDTO.class);
        return "redirect:/paginado"; 
	}
	
    @GetMapping("/editar/{id}")
    public String showFormToEdit(@PathVariable Long id, Model model) {
    	ResponseEntity<BookResponseDTO> book = this.restTemplate.getForEntity(this.apiUrl + '/' + id, BookResponseDTO.class);
        model.addAttribute("book", book.getBody());
        model.addAttribute("titulo", "Editar Libro");
        model.addAttribute("action", "/actualizar");
        return "new-form";
    }

    @PostMapping("/actualizar")
    public String update(BookRequestDTO bookRequestDTO) {
    	this.restTemplate.put(this.apiUrl + '/' + bookRequestDTO.getId(), bookRequestDTO);
        return "redirect:/paginado"; 
    }
    
    @GetMapping("/eliminar/{id}")
    public String showFormToDelete(@PathVariable Long id, Model model) {
    	ResponseEntity<BookResponseDTO> book = this.restTemplate.getForEntity(this.apiUrl + '/' + id, BookResponseDTO.class);
        model.addAttribute("book", book.getBody());
        model.addAttribute("titulo", "Eliminar Libro");
        model.addAttribute("action", "/borrar?id=" + book.getBody().getId());
        return "new-form";
    }

    @PostMapping("/borrar")
    public String delete(@RequestParam Long id) {
    	this.restTemplate.delete(this.apiUrl + '/' + id);
        return "redirect:/paginado"; 
    }
}
