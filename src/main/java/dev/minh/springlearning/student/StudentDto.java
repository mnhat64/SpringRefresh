package dev.minh.springlearning.student;

import jakarta.validation.constraints.NotEmpty;

public record StudentDto(
    @NotEmpty(message = "First name should not be empty")
    String firstname,
    @NotEmpty(message = "First name should not be empty")
    String lastname,
    String email,
    Integer schoolId
){
}
