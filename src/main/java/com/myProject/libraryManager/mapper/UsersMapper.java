package com.myProject.libraryManager.mapper;

import com.myProject.libraryManager.dto.request.BooksDTO;
import com.myProject.libraryManager.dto.request.UserDTO;
import com.myProject.libraryManager.entity.Books;
import com.myProject.libraryManager.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UsersMapper {

    UsersMapper INSTANCE = Mappers.getMapper(UsersMapper.class);

    User toModel(UserDTO userDTO);

    UserDTO toDTO(User userEntity);

}
