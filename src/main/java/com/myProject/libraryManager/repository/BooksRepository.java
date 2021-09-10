package com.myProject.libraryManager.repository;

import com.myProject.libraryManager.entity.Books;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface BooksRepository extends JpaRepository<Books, Long> {

    List<Books> findByAuthorContainingIgnoreCase(String author);
    List<Books> findByAvailableTrue();

}