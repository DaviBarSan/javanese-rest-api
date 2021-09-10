package com.myProject.libraryManager.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BooksRental {

    @Id()
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long rentalId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_rent_id")
    private User userRent;

    @OneToMany(targetEntity = Books.class
            ,fetch = FetchType.LAZY)
    private List<Books> booksRented;

    @Column(nullable = false)
    private LocalDate pickUpDate;

    @Column(nullable = false)
    private LocalDate returnDate;

}
