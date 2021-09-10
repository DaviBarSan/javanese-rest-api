package com.myProject.libraryManager.dto.request;

import com.myProject.libraryManager.entity.BooksRental;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {

    private Long id;

    @NotEmpty
    @Size(min=2 , max = 50)
    private String firstName;

    @NotEmpty
    @Size(min = 2, max = 50)
    private String lastName;

    @Email
    @Size(min = 2, max = 50)
    private String email;

    private List<BooksRental> rentalActivity;

}

