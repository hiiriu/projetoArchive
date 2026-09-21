package com.classes.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record CreatePoster(
    @NotBlank(message = "Title is required") @Size(min = 3, max = 30, message = "Title must be between 3 and 30 characters") String title,
    @Size(min = 0, max = 200, message = "Description can't be longer than 200 characters ") String description,
    @Pattern(regexp = "^[a-zA-Z0-9-]+$", message = "Location can only contain letters, digits and dashes") String location,
    String links,
    @NotBlank(message = "An image is required") String img  //alterar nome para posterID.fileType

){}
 
