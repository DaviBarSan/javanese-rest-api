package com.myProject.libraryManager.mapper;

import com.myProject.libraryManager.dto.request.BooksDTO;
import com.myProject.libraryManager.entity.Books;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BooksMapper {

    BooksMapper INSTANCE = Mappers.getMapper(BooksMapper.class);

    Books toModel(BooksDTO booksDTO);

    BooksDTO toDTO(Books books);

}
