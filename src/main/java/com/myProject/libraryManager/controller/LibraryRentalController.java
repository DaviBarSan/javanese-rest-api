package com.myProject.libraryManager.controller;

import com.myProject.libraryManager.dto.request.BooksRentalDTO;
import com.myProject.libraryManager.dto.response.LogResponseDTO;
import com.myProject.libraryManager.entity.BooksRental;
import com.myProject.libraryManager.exceptions.UserNotFoundException;
import com.myProject.libraryManager.service.LibraryRentalService;
import com.myProject.libraryManager.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/library/rent")
public class LibraryRentalController {

    LibraryRentalService libraryRentalService;
    UsersService usersService;

    @Autowired
    public LibraryRentalController(LibraryRentalService libraryRentalService, UsersService usersService) {
        this.libraryRentalService = libraryRentalService;
        this.usersService = usersService;
    }

    @GetMapping(path = "/{userId}/home")
    public LogResponseDTO userHomeByItsId(@PathVariable Long userId) throws UserNotFoundException {
        return libraryRentalService.helloUser(userId);
    }

    @PostMapping(path = "/{userId}/{booksId}")
    public LogResponseDTO rentBooksById(@PathVariable Long userId, @PathVariable String booksId) throws UserNotFoundException {
        return libraryRentalService.rentBooksByUserIdAndBooksId(userId, booksId);
    }

    @GetMapping(path = "/{userId}/activity")
    public List<BooksRental> getRentalActivity(@PathVariable Long userId) throws UserNotFoundException {
        return libraryRentalService.getRentalActivityByUserId(userId);
    }
}
