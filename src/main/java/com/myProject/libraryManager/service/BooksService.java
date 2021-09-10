package com.myProject.libraryManager.service;

import com.myProject.libraryManager.dto.request.BooksDTO;
import com.myProject.libraryManager.dto.response.LogResponseDTO;
import com.myProject.libraryManager.entity.Books;
import com.myProject.libraryManager.exceptions.BookNotFoundException;
import com.myProject.libraryManager.mapper.BooksMapper;
import com.myProject.libraryManager.repository.BooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BooksService {

    private final BooksMapper booksMapper = BooksMapper.INSTANCE;
    private BooksRepository booksRepository;

    @Autowired
    public BooksService(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    public LogResponseDTO createABookInDB(@Valid BooksDTO currBookDTO){
        Books newBook = booksMapper.toModel(currBookDTO);
        Books savedBook = booksRepository.save(newBook);
        return createLogMessage(savedBook);
    }

    public Books getBookById(Long id) throws BookNotFoundException {
        Books bookFound = booksRepository.findById(id).orElseThrow(
                () -> new BookNotFoundException("No books found with this " + id)
        );
        return bookFound;
    }

    public String listAllBooks() {
        if (booksRepository.findAll().isEmpty()) return "None book found in MyLibrary!";
        List<BooksDTO> allBooksDTOList = booksRepository.findAll().stream()
                .map(currBook -> booksMapper.toDTO(currBook))
                .collect(Collectors.toList());
        StringBuilder sb = new StringBuilder();
        return displayListAsHTML(allBooksDTOList, sb);
    }
    public String listAllAvailableBooks() {
        if (booksRepository.findByAvailableTrue().isEmpty()) return "None book available found in MyLibrary!";
        List<BooksDTO> allBooksDTOList = booksRepository.findByAvailableTrue().stream()
                .map(currBook -> booksMapper.toDTO(currBook))
                .collect(Collectors.toList());
        StringBuilder sb = new StringBuilder();
        return displayListAsHTML(allBooksDTOList, sb);
    }

    public List<BooksDTO> findAllBooksByAuthor(String author){
        List<Books> allBooksFromAuthor = booksRepository.findByAuthorContainingIgnoreCase(author);
        List<BooksDTO> booksDTOList = allBooksFromAuthor.stream()
                .map(books -> booksMapper.toDTO(books))
                .collect(Collectors.toList());
        return booksDTOList;
    }
    public String displayListAsHTML(Collection<BooksDTO> collection,StringBuilder sb){
        sb.append("<p>");
        collection.stream()
                .forEach(bookDTO -> sb.append(bookDTO.getTitle() + " by "
                        + bookDTO.getAuthor()
                        + "! Take a look into "
                        + "<a href=" + bookDTO.getLink() + ">link to this book</a>"
                        + " ID: " + bookDTO.getId()
                        +  "<br>"));
        sb.append("</p>");
        return sb.toString();
    }

    private static LogResponseDTO createLogMessage(Books newBook) {
        return LogResponseDTO.builder()
                .message("The book " + newBook.getId() + " was successfully saved!")
                .build();
    }

}
