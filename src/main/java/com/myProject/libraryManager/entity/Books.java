package com.myProject.libraryManager.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Books {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false)
    private String country;

    @Column
    private String imageLink;

    @Column
    private String language;

    @Column
    private String link;

    @Column
    private int pages;

    @Column(nullable = false)
    private String title;

    @Column
    private int year;

    @Column(nullable = false)
    private boolean available;

    @ManyToOne(targetEntity = BooksRental.class,
        fetch = FetchType.EAGER)
    @JoinColumn(name = "book_rental_id")
    private BooksRental bookRental;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Books books = (Books) o;

        return Objects.equals(id, books.id);
    }

    @Override
    public int hashCode() {
        return 1721869384;
    }
}
