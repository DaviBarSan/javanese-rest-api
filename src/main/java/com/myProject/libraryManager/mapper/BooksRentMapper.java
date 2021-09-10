package com.myProject.libraryManager.mapper;

import com.myProject.libraryManager.dto.request.BooksRentDTO;
import com.myProject.libraryManager.entity.BooksRental;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BooksRentMapper {

    BooksRentMapper INSTANCE = Mappers.getMapper(BooksRentMapper.class);

    BooksRental toModel(BooksRentDTO booksRentDTO);

    BooksRentDTO toDTO(BooksRental booksRental);

}
