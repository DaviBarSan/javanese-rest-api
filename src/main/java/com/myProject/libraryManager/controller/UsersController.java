package com.myProject.libraryManager.controller;

import com.myProject.libraryManager.dto.request.UserDTO;
import com.myProject.libraryManager.dto.response.LogResponseDTO;
import com.myProject.libraryManager.exceptions.UserNotFoundException;
import com.myProject.libraryManager.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/library/users")
public class UsersController {

    UsersService usersService;

    @Autowired
    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @PostMapping("/create")
    public LogResponseDTO createUserInLib(@Valid @RequestBody UserDTO userDTO){
        return usersService.createUserInDB(userDTO);
    }

    @GetMapping("/{id}")
    public LogResponseDTO getUserById(@PathVariable Long id) throws UserNotFoundException {
        UserDTO userDTOfound = usersService.getUserById(id);
        return LogResponseDTO.builder()
                .message(
                        "The user " + userDTOfound.getFirstName()
                                + " " + userDTOfound.getLastName()
                                + " was successfully found!").build();
    }

    @DeleteMapping("/delete/{id}")
    public LogResponseDTO deleteUserById(@PathVariable Long id) throws UserNotFoundException{
        usersService.deleteUser(id);
        return LogResponseDTO.builder()
                .message("User deleted successfully!").build();
    }

    @PutMapping("/{id}/update")
    public LogResponseDTO updateUser(@Valid @RequestBody UserDTO newUser, @PathVariable Long id) throws UserNotFoundException {
        usersService.updateUserById(newUser, id);
        return LogResponseDTO.builder().message("User with name "
                + newUser.getFirstName() + " "
                + newUser.getLastName()
                + ". Current email is " + newUser.getEmail()).build();
    }




}
