package com.example.studentserviceapplication.web.rest;


import com.example.studentserviceapplication.service.RatingService;
import com.example.studentserviceapplication.service.TeacherService;
import com.example.studentserviceapplication.service.dto.TeacherDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teacher")
public class TeacherResource {

    private final TeacherService teacherService;
    private final RatingService ratingService;

    public TeacherResource(TeacherService teacherService, RatingService ratingService) {
        this.teacherService = teacherService;
        this.ratingService = ratingService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeacherDTO> getTeacher(@PathVariable long id){
      TeacherDTO teacherDTO =   teacherService.getTeacherById(id) ;
      return ResponseEntity.ok().body(teacherDTO) ;
    }

    @GetMapping("/all-teachers")
    public ResponseEntity<List<TeacherDTO>> getAllTeachers (){
        List<TeacherDTO> teacherDTOS = teacherService.getAllTeachers();
        return ResponseEntity.ok().body(teacherDTOS) ;
    }

/*    @GetMapping("/all-teachers-from-web")
    public ResponseEntity<List<Teacher>> getAllTeachersFromWeb (){
        List<Teacher> teacherDTOS = teacherService.getAllTeachersFromWeb();
        return ResponseEntity.ok().body(teacherDTOS) ;
    }*/

    @GetMapping("/detect-swear-words")
    public ResponseEntity<List<TeacherDTO>> detectSwearWords() {
        List<TeacherDTO> teacherDTOS = teacherService.getAllTeachers();
        return ResponseEntity.ok().body(teacherDTOS);
    }

    @PostMapping("/{teacherId}/ratings")
    public ResponseEntity<?> addRating(@PathVariable Long teacherId,
                                       @RequestParam int score,
                                       @RequestHeader("X-User-Id") String userId) {
        ratingService.addRating(teacherId, score, userId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{teacherId}/ratings/average")
    public ResponseEntity<Double> getAverageRating(@PathVariable Long teacherId) {
        double averageRating = ratingService.calculateAverageRating(teacherId);
        return ResponseEntity.ok(averageRating);
    }
}
