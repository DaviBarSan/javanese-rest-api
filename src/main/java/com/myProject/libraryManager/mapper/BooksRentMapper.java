package com.myProject.libraryManager.mapper;

import com.myProject.libraryManager.dto.request.BooksRentalDTO;
import com.myProject.libraryManager.entity.BooksRental;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BooksRentMapper {

    BooksRentMapper INSTANCE = Mappers.getMapper(BooksRentMapper.class);

    BooksRental toModel(BooksRentalDTO booksRentalDTO);

    BooksRentalDTO toDTO(BooksRental booksRental);

}
