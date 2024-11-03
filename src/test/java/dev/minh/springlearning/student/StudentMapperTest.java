package dev.minh.springlearning.student;

import org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class StudentMapperTest {

    private StudentMapper mapper;

    @BeforeEach
    void setUp(){
        mapper = new StudentMapper();
    }

    @Test
    void toStudent() {
        StudentDto dto = new StudentDto(
                "John", "Doe", "john@gmail.com", 1);

        Student student = mapper.toStudent(dto);
        assertEquals(dto.firstname(), student.getFirstname());
        assertEquals(dto.lastname(), student.getLastname());
        assertEquals(dto.email(), student.getEmail());
        assertNotNull(student.getSchool());
        assertEquals(student.getSchool().getId(), dto.schoolId());

    }

    @Test
    void toStudentResponseDto() {
        Student student = new Student(
                "Jane",
                "Doe",
                "jane@gmail.com",
                20);

        StudentResponseDto responseDto = mapper.toStudentResponseDto(student);
        assertEquals(responseDto.firstname(), student.getFirstname());
        assertEquals(responseDto.lastname(), student.getLastname());
        assertEquals(responseDto.email(), student.getEmail());
    }

    @Test
    void throwingException(){
        var exp = assertThrows(NullPointerException.class, ()->mapper.toStudent(null));
        assertEquals(exp.getMessage(), "DTO should not be null.");
    }
}