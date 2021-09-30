package com.myProject.libraryManager.repository;

import com.myProject.libraryManager.entity.Books;
import com.myProject.libraryManager.entity.BooksRental;
import com.myProject.libraryManager.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LibraryRentalRepository extends JpaRepository<BooksRental, Long> {
    List<BooksRental> findByUserRent(User userRent);
}
