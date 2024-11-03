package dev.minh.springlearning.student;

import dev.minh.springlearning.student.StudentDto;
import dev.minh.springlearning.student.StudentMapper;
import dev.minh.springlearning.student.StudentRepository;
import dev.minh.springlearning.student.StudentResponseDto;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    public StudentService(StudentRepository studentRepository, StudentMapper studentMapper) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
    }

    public StudentResponseDto saveStudent(
            StudentDto dto
    ){
        var student = studentMapper.toStudent(dto);
        var savedStudent = studentRepository.save(student);
        return studentMapper.toStudentResponseDto(savedStudent);
    }

    public List<StudentResponseDto> findAllStudents(){
        return studentRepository.findAll()
                .stream()
                .map(studentMapper::toStudentResponseDto)
                .collect(Collectors.toList());
    }

    public StudentResponseDto findStudentById(
            @PathVariable("student-id") Integer id
    ){
        return studentRepository.findById(id)
                .map(studentMapper::toStudentResponseDto).orElse(null);
    }

    public List<StudentResponseDto> findStudentByName(
            @PathVariable("student-name") String name
    ){
        return studentRepository.findAllByFirstnameContaining(name)
                .stream()
                .map(studentMapper::toStudentResponseDto)
                .collect(Collectors.toList());
    }

    public void delete(
            @PathVariable("student-id") Integer id
    ){
        studentRepository.deleteById(id);
    }

}
