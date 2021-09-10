package com.myProject.libraryManager.dto.request;

import com.myProject.libraryManager.entity.Books;
import com.myProject.libraryManager.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BooksRentDTO {

    private Long rentalID;

    @NotNull
    private User userRent;

    @NotNull
    private List<Books> booksRented;

    @NotNull
    private LocalDate pickUpDate;

    @NotNull
    private LocalDate returnDate;
}
