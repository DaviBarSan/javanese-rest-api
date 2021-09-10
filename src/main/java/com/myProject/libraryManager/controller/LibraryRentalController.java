package com.myProject.libraryManager.controller;

import com.myProject.libraryManager.dto.request.BooksRentDTO;
import com.myProject.libraryManager.dto.request.UserDTO;
import com.myProject.libraryManager.dto.response.LogResponseDTO;
import com.myProject.libraryManager.entity.BooksRental;
import com.myProject.libraryManager.exceptions.UserNotFoundException;
import com.myProject.libraryManager.service.LibraryRentalService;
import com.myProject.libraryManager.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
    public LogResponseDTO getAvailableBooks(@PathVariable Long userId) throws UserNotFoundException {
        return libraryRentalService.helloUser(userId);
    }

    @PostMapping(path = "/{userId}/{booksId}")
    public LogResponseDTO rentBooksById(@PathVariable Long userId, @PathVariable String booksId) throws UserNotFoundException {
        return libraryRentalService.rentBooksByUserIdAndBooksId(userId, booksId);
    }

    @GetMapping(path = "/{userId}/activity")
    public UserDTO getRentalActivity(@PathVariable Long userId) throws UserNotFoundException {
        return usersService.getUserById(userId);
    }
}
