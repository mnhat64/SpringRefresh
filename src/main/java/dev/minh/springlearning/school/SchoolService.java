package dev.minh.springlearning.school;

import dev.minh.springlearning.school.SchoolDto;
import dev.minh.springlearning.school.SchoolMapper;
import dev.minh.springlearning.school.SchoolRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SchoolService {

    private final SchoolRepository schoolRepository;
    private final SchoolMapper schoolMapper;

    public SchoolService(SchoolRepository schoolRepository, SchoolMapper schoolMapper) {
        this.schoolRepository = schoolRepository;
        this.schoolMapper = schoolMapper;
    }


    @PostMapping("/schools")
    public SchoolDto create(
            @RequestBody SchoolDto dto){
        var school = schoolMapper.toSchool(dto);
        var savedSchool = schoolRepository.save(school);
        return dto;
    }

    @GetMapping("/schools")
    public List<SchoolDto> findAll(){
        return schoolRepository.findAll()
                .stream()
                .map(schoolMapper::toSchoolDto)
                .collect(Collectors.toList());
    }
}
