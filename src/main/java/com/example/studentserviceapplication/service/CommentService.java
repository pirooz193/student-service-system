package com.example.studentserviceapplication.service;

import com.example.studentserviceapplication.service.dto.CommentDTO;

import java.util.Set;

public interface CommentService {
    CommentDTO saveComment(long teacherId, CommentDTO commentDTO);

    Set<CommentDTO> getRequiredTeacherComments(String teacherId);

    void deleteComment(long commentId, long teacherId);
}
