package dev.minh.springlearning.student;

import dev.minh.springlearning.school.School;
import org.springframework.stereotype.Service;

@Service
public class StudentMapper {
    public Student toStudent(StudentDto dto){
        if (dto == null){
            throw new NullPointerException("DTO should not be null.");
        }
        var student = new Student();
        student.setFirstname(dto.firstname());
        student.setLastname(dto.lastname());
        student.setEmail(dto.email());

        var school = new School();
        school.setId(dto.schoolId());

        student.setSchool(school);

        return student;
    }

    public StudentResponseDto toStudentResponseDto(Student student){
        return new StudentResponseDto(student.getFirstname(), student.getLastname(), student.getEmail());
    }


}
