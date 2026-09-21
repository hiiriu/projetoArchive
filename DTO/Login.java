package com.classes.DTO;

import jakarta.validation.constraints.NotBlank;

public record Login(
        @NotBlank(message = "Username is required") String username,
        @NotBlank(message = "Password is required") String password
        ){}
