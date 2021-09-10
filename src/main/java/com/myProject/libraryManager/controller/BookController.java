package com.myProject.libraryManager.controller;

import com.myProject.libraryManager.dto.request.BooksDTO;
import com.myProject.libraryManager.dto.response.LogResponseDTO;
import com.myProject.libraryManager.exceptions.BookNotFoundException;
import com.myProject.libraryManager.service.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.*;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/library/books")
public class BookController {

    private BooksService booksService;
    private ValidatorFactory bookValidator;

    @Autowired
    public BookController(BooksService booksService) {
        this.booksService = booksService;
        this.bookValidator = Validation.buildDefaultValidatorFactory();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    private LogResponseDTO createBookinLibrary(@RequestBody BooksDTO bookDTO){
        //Library created with Json provided by https://github.com/benoitvallon/100-best-books/blob/master/books.json;
        formatJsonInputFromCurrentSource(bookDTO);
        if (verifyAllConstrains(bookDTO)) booksService.createABookInDB(bookDTO);
        return LogResponseDTO.builder().message("Constrain violation in book " + bookDTO.getTitle()).build();
    }

    @PostMapping(path = "/insertalist")
    @ResponseStatus(HttpStatus.CREATED)
    private LogResponseDTO createBooksFromList(@RequestBody List<BooksDTO> booksDTO){
        booksDTO.stream().forEach(currBookDTO -> createBookinLibrary(currBookDTO));
        return LogResponseDTO.builder().message("All books were added to MyLibrary!").build();
    }

    @GetMapping(path = "/")
    public String listAllBooks(){
        return booksService.listAllBooks();
    }

    @GetMapping(path = "/{author}")
    public List<BooksDTO> listAllBooksByAuthor(@PathVariable String author) throws BookNotFoundException {
        List<BooksDTO> allBooksByAuthor = booksService.findAllBooksByAuthor(author);
        if (allBooksByAuthor.isEmpty()) throw new BookNotFoundException("We have not any books by this author!");
        return allBooksByAuthor;
    }

    @GetMapping(path = "/{author}/display")
    public String displayAllBooksByAuthor(@PathVariable String author){
        List<BooksDTO> allBooksByAuthor = booksService.findAllBooksByAuthor(author);
        if (allBooksByAuthor.isEmpty()) return "We have not any books by this author!";
        StringBuilder sb = new StringBuilder();
        return booksService.displayListAsHTML(allBooksByAuthor, sb);
    }

    private void formatJsonInputFromCurrentSource(BooksDTO booksDTO) {
        String formatedLink = booksDTO.getLink().substring(0, booksDTO.getLink().length()-1);
        booksDTO.setLink(formatedLink);
        booksDTO.setAvailable(true);
    }

    private boolean verifyAllConstrains(BooksDTO currBookDTO){
        Set<ConstraintViolation<BooksDTO>> constraintViolationSet = bookValidator.getValidator().validate(currBookDTO);
        return constraintViolationSet.isEmpty();
    }

}
