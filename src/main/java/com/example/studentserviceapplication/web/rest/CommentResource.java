package com.example.studentserviceapplication.web.rest;

import com.example.studentserviceapplication.service.CommentService;
import com.example.studentserviceapplication.service.dto.CommentDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Set;

@RestController
@RequestMapping("/api/comment")
public class CommentResource {
    private final CommentService commentService;

    public CommentResource(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/save-comment/{teacherId}")
    public ResponseEntity<CommentDTO> saveComment(@PathVariable long teacherId, @RequestBody CommentDTO commentDTO) throws URISyntaxException {
        CommentDTO savedComment = commentService.saveComment(teacherId, commentDTO);
        return ResponseEntity.created(new URI("/api/comment/save-comment")).body(savedComment);
    }

    @PutMapping("/update-comment/{teacherId}")
    public ResponseEntity<CommentDTO> updateComment(@PathVariable long teacherId, @RequestBody CommentDTO commentDTO) throws URISyntaxException {
        CommentDTO savedComment = commentService.saveComment(teacherId, commentDTO);
        return ResponseEntity.created(new URI("/api/comment/save-comment")).body(savedComment);
    }

    @GetMapping("/{teacherId}")
    public ResponseEntity<Set<CommentDTO>> getRequiredTeacherComments(@PathVariable String teacherId) {
        Set<CommentDTO> comments = commentService.getRequiredTeacherComments(teacherId);
        return ResponseEntity.ok().body(comments);
    }

    @DeleteMapping("/{teacherId}/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable long commentId, @PathVariable long teacherId) {
        commentService.deleteComment(commentId, teacherId);
        return ResponseEntity.ok().build();
    }
}
