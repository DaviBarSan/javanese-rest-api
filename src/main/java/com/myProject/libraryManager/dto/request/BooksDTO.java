package com.myProject.libraryManager.dto.request;

import com.myProject.libraryManager.entity.BooksRental;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BooksDTO {

    private Long id;

    @NotEmpty
    private String author;

    @NotEmpty
    private String country;

    @NotEmpty
    private String imageLink;

    @NotEmpty
    private String language;

    @URL
    private String link;

    private int pages;

    @NotEmpty
    private String title;

    private int year;

    @AssertTrue
    @NotNull
    private boolean available;

    private BooksRental bookRental;

}
