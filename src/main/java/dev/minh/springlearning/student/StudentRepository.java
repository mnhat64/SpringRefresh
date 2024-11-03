package dev.minh.springlearning.student;

import dev.minh.springlearning.student.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Integer> {
    List<Student> findAllByFirstnameContaining(String p);
}
