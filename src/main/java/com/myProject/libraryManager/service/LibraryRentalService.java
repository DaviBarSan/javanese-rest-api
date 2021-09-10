package com.myProject.libraryManager.service;

import com.myProject.libraryManager.dto.request.BooksDTO;
import com.myProject.libraryManager.dto.request.UserDTO;
import com.myProject.libraryManager.dto.response.LogResponseDTO;
import com.myProject.libraryManager.entity.Books;
import com.myProject.libraryManager.entity.BooksRental;
import com.myProject.libraryManager.entity.User;
import com.myProject.libraryManager.exceptions.BookNotFoundException;
import com.myProject.libraryManager.exceptions.UserNotFoundException;
import com.myProject.libraryManager.mapper.BooksRentMapper;
import com.myProject.libraryManager.repository.LibraryRentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LibraryRentalService {

    private final UsersService userService;
    private final BooksService booksService;
    LibraryRentalRepository libraryRentalRepository;
    BooksRentMapper booksRentMapper = BooksRentMapper.INSTANCE;

    @Autowired
    public LibraryRentalService(LibraryRentalRepository libraryRentalRepository, UsersService usersService, BooksService booksService) {
        this.libraryRentalRepository = libraryRentalRepository;
        this.userService = usersService;
        this.booksService = booksService;
    }


    public LogResponseDTO helloUser(Long id) throws UserNotFoundException {
        UserDTO rentalUser = userService.getUserById(id);
        String availableBooks = booksService.listAllAvailableBooks();
        return LogResponseDTO.builder().message(
                "Hello, " + rentalUser.getFirstName() + "!"
                + " Today the available books are: "
                + "<br> </br>"
                + availableBooks
        ).build();
    }

    public LogResponseDTO rentBooksByUserIdAndBooksId(Long id , String booksId) throws UserNotFoundException {
        List<Books> allBooks = Arrays.stream(booksId.split("-"))
                .map(bookString -> {
                    try {
                        Books currBook = booksService.getBookById(Long.parseLong(bookString));
                        currBook.setAvailable(false);
                        return currBook;
                    } catch (BookNotFoundException e) {
                        System.out.print(e.getMessage());
                    } return null;
                })
                .collect(Collectors.toList());
        LocalDate today = LocalDate.now();
        User userRenting = userService.getUserEntityById(id);
        BooksRental currRental = BooksRental.builder()
                .booksRented(allBooks)
                .pickUpDate(today)
                .returnDate(today.plusWeeks(1L))
                .userRent(userRenting).build();
        libraryRentalRepository.save(currRental);
        return LogResponseDTO.builder().message(
                "New rental done, " + currRental.getUserRent().getFirstName() + "!")
                .build();
    }

    public BooksRental getRentalActivityByUserId(Long userId) {
        BooksRental rentalActivity = libraryRentalRepository.findByUserRentId(userId);
        return rentalActivity;
    }
}
