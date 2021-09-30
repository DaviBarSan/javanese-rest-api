package com.myProject.libraryManager.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_rent_id")
    @JsonBackReference
    private User userRent;

    @OneToMany(targetEntity = Books.class
            ,fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Books> booksRented;

    @Column(nullable = false)
    private LocalDate pickUpDate;

    @Column(nullable = false)
    private LocalDate returnDate;

}
