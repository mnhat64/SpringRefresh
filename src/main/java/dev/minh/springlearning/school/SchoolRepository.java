package dev.minh.springlearning.school;

import dev.minh.springlearning.school.School;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchoolRepository extends JpaRepository<School, Integer> {

}
