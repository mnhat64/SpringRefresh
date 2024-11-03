package dev.minh.springlearning.student;

public record StudentResponseDto(
        String firstname,
        String lastname,
        String email
) {
}
