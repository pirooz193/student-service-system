package com.example.studentserviceapplication.web.rest;


import com.example.studentserviceapplication.domain.Teacher;
import com.example.studentserviceapplication.service.TeacherService;
import com.example.studentserviceapplication.service.dto.TeacherDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/teacher/")
public class TeacherResource {

    private final TeacherService teacherService;

    public TeacherResource(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping("{id}")
    public ResponseEntity<TeacherDTO> getTeacher(@PathVariable long id){
      TeacherDTO teacherDTO =   teacherService.getTeacherById(id) ;
      return ResponseEntity.ok().body(teacherDTO) ;
    }

    @GetMapping("/all-teachers")
    public ResponseEntity<List<TeacherDTO>> getAllTeachers (){
        List<TeacherDTO> teacherDTOS = teacherService.getAllTeachers();
        return ResponseEntity.ok().body(teacherDTOS) ;
    }

//    @GetMapping("/all-teachers-from-web")
//    public ResponseEntity<List<Teacher>> getAllTeachersFromWeb (){
//        List<Teacher> teacherDTOS = teacherService.getAllTeachersFromWeb();
//        return ResponseEntity.ok().body(teacherDTOS) ;
//    }

    @GetMapping("/detect-swear-words")
     public ResponseEntity<List<TeacherDTO>> detectSwearWords (){
        List<TeacherDTO> teacherDTOS = teacherService.getAllTeachers();
        return ResponseEntity.ok().body(teacherDTOS) ;
    }
}
