package com.example.studentserviceapplication.service;

import com.example.studentserviceapplication.service.dto.CommentDTO;

import java.util.List;

public interface CommentService {
    CommentDTO saveComment(long teacherId, CommentDTO commentDTO);

    List<CommentDTO> getRequiredTeacherComments(String teacherId);
}
