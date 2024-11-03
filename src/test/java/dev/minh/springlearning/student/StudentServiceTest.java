package dev.minh.springlearning.student;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.SimpleTimeZone;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

class StudentServiceTest {

    @InjectMocks
    private StudentService studentService;

    @Mock
    private StudentMapper mapper;

    @Mock
    private StudentRepository repository;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void saveStudentTest(){
        StudentDto dto = new StudentDto("Minh", "Phan", "kitchy@gmail.com", 1);
        Student student = new Student("Minh", "Phan", "kitchy@gmail.com", 20);
        Student savedStudent = new Student("Minh", "Phan", "kitchy@gmail.com", 20);

        savedStudent.setId(1);

        Mockito.when(mapper.toStudent(dto)).thenReturn(student);
        Mockito.when(repository.save(student)).thenReturn(savedStudent);
        Mockito.when(mapper.toStudentResponseDto(savedStudent)).thenReturn(new StudentResponseDto("Minh", "Phan", "kitchy@gmail.com"));

        StudentResponseDto responseDto = studentService.saveStudent(dto);

        assertEquals(dto.firstname(), responseDto.firstname());
        assertEquals(dto.lastname(), responseDto.lastname());
        assertEquals(dto.email(), responseDto.email());

        verify(mapper, Mockito.times(1)).toStudent(dto);
        verify(repository, Mockito.times(1)).save(student);
        verify(mapper, Mockito.times(1)).toStudentResponseDto(savedStudent);
    }

    @Test
    public void returnAllStudent(){
        List<Student> students = new ArrayList<>();
        Student student = new Student("Minh", "Phan", "kitchy@gmail.com", 20);
        students.add(student);

        Mockito.when(repository.findAll()).thenReturn(students);
        Mockito.when(mapper.toStudentResponseDto(any(Student.class))).thenReturn(new StudentResponseDto("Minh", "Phan", "kitchy@gmail.com"));


        List<StudentResponseDto> responseDtos = studentService.findAllStudents();

        assertEquals(students.size(), responseDtos.size());
    }

    @Test
    public void findStudentById(){
        Student student = new Student("Minh", "Phan", "kitchy@gmail.com", 20);
        student.setId(1);

        Mockito.when(repository.findById(1)).thenReturn(Optional.of(student));
        Mockito.when(mapper.toStudentResponseDto(any(Student.class)))
                .thenReturn(new StudentResponseDto(
                        "Minh",
                        "Phan",
                        "kitchy@gmail.com"
                ));

        StudentResponseDto dto = studentService.findStudentById(1);

        assertEquals(dto.lastname(), student.getLastname());
        assertEquals(dto.firstname(), student.getFirstname());
        assertEquals(dto.email(), student.getEmail());


    }

}