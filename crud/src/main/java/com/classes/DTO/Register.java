package com.classes.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Email;

public record Register(
        @NotBlank(message = "Username is required") @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters") @Pattern(regexp = "^[a-zA-Z0-9._-]+$", message = "Username can only contain letters, digits, dots, underscores and dashes") String username,
        @NotBlank(message = "Password is required") @Size(min = 8, max = 64, message = "Password must be between 8 and 64 characters") String password,
        @NotBlank(message = "Email is required") @Email(message = "This email is not valid.") String email,
        String displayName
    ) {
}
