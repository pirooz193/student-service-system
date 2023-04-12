package com.example.studentserviceapplication.web.rest;

import com.example.studentserviceapplication.domain.Faculty;
import com.example.studentserviceapplication.service.FacultyService;
import com.example.studentserviceapplication.service.dto.FacultyDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/faculty")
public class FacultyResource {
    private final FacultyService facultyService;

    public FacultyResource(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @GetMapping("/all-faculties")
    public ResponseEntity<List<Faculty>> getAllFaculties() {
        List<Faculty> faculties = facultyService.getAll();
        return ResponseEntity.ok().body(faculties);
    }

    @GetMapping("/{code}")
    public ResponseEntity<FacultyDTO> getFacultyByCode(@PathVariable String code) {
        FacultyDTO facultyDTO = facultyService.getFacultyByCode(code);
        return ResponseEntity.ok().body(facultyDTO);
    }
}
