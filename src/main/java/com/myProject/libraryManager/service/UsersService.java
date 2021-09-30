package com.myProject.libraryManager.service;

import com.myProject.libraryManager.dto.request.UserDTO;
import com.myProject.libraryManager.dto.response.LogResponseDTO;
import com.myProject.libraryManager.entity.BooksRental;
import com.myProject.libraryManager.entity.User;
import com.myProject.libraryManager.exceptions.UserNotFoundException;
import com.myProject.libraryManager.mapper.UsersMapper;
import com.myProject.libraryManager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;

@Service
public class UsersService {

    private final UsersMapper usersMapper = UsersMapper.INSTANCE;
    private UserRepository userRepository;

    @Autowired
    public UsersService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public LogResponseDTO createUserInDB(@Valid UserDTO inputUserToSave){
        User userToSave = usersMapper.toModel(inputUserToSave);
        userRepository.save(userToSave);
        return LogResponseDTO.builder()
                .message("The user with name " + userToSave.getFirstName()
                        + " "
                        + userToSave.getLastName()
                        + " was saved successfully!")
                .build();
    }

    public UserDTO getUserById(Long id) throws UserNotFoundException {
        verifyIfUserExists(id);
        UserDTO userDTO = usersMapper.toDTO(userRepository.findById(id).get());
        return userDTO;
    }

    public User getUserEntityById(Long id) throws UserNotFoundException {
        return verifyIfUserExists(id);
    }

    public void updateUserById(UserDTO newUserDTO, Long id) throws UserNotFoundException {
        verifyIfUserExists(id);
        User newUser = usersMapper.toModel(newUserDTO);
        userRepository.deleteById(id);
        userRepository.save(newUser);
    }

    public void deleteUser (Long id) throws UserNotFoundException {
        verifyIfUserExists(id);
        userRepository.deleteById(id);
    }

    public List<BooksRental> getRentalActivityByUserId(Long id) throws UserNotFoundException {
        User currUser = verifyIfUserExists(id);
        return currUser.getRentalActivity();
    }


    private User verifyIfUserExists(Long id) throws UserNotFoundException {
         return userRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException("User not registred in MyLibrary!"));
    }
}
