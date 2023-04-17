package com.example.studentserviceapplication.service.impl;

import com.example.studentserviceapplication.repository.CommentRepository;
import com.example.studentserviceapplication.service.CommentService;
import com.example.studentserviceapplication.service.TeacherService;
import com.example.studentserviceapplication.service.dto.CommentDTO;
import com.example.studentserviceapplication.service.dto.TeacherDTO;
import com.example.studentserviceapplication.service.mapper.CommentMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;
    private final TeacherService teacherService;

    public CommentServiceImpl(CommentRepository commentRepository, CommentMapper commentMapper, TeacherService teacherService) {
        this.teacherService = teacherService;
        this.commentRepository = commentRepository;
        this.commentMapper = commentMapper;
    }

    @Override
    public CommentDTO saveComment(long teacherId, CommentDTO commentDTO) {
        commentDTO.setDateTime(LocalDateTime.now());
        TeacherDTO teacher = teacherService.getTeacherById(teacherId);
        teacher.getComments().add(commentDTO);
        teacherService.save(teacher);
        return commentMapper.toDTO(commentRepository.save(commentMapper.toEntity(commentDTO)));

    }

    @Override
    public List<CommentDTO> getRequiredTeacherComments(String teacherId) {
        TeacherDTO teacher = teacherService.getTeacherById(Long.parseLong(teacherId));

        return teacher.getComments();
    }
}
