package com.example.studentserviceapplication.service.impl;

import com.example.studentserviceapplication.domain.Comment;
import com.example.studentserviceapplication.repository.CommentRepository;
import com.example.studentserviceapplication.service.CommentService;
import com.example.studentserviceapplication.service.TeacherService;
import com.example.studentserviceapplication.service.UserService;
import com.example.studentserviceapplication.service.dto.CommentDTO;
import com.example.studentserviceapplication.service.dto.TeacherDTO;
import com.example.studentserviceapplication.service.mapper.CommentMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Set;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final UserService userService;
    private final CommentMapper commentMapper;
    private final TeacherService teacherService;

    public CommentServiceImpl(CommentRepository commentRepository, UserService userService, CommentMapper commentMapper, TeacherService teacherService) {
        this.userService = userService;
        this.teacherService = teacherService;
        this.commentRepository = commentRepository;
        this.commentMapper = commentMapper;
    }

    @Override
    public CommentDTO saveComment(long teacherId, CommentDTO commentDTO) {
        final Comment comment = commentMapper.toEntity(commentDTO);
        comment.setDateTime(LocalDateTime.now().plusHours(2).plusMinutes(30));
        comment.setUser(userService.getUserByKey(commentDTO.getUser().getKey()));
        CommentDTO finalComment = commentMapper.toDTO(comment);
        TeacherDTO teacher = teacherService.getTeacherById(teacherId);
        teacher.getComments().add(finalComment);
        teacher.setRate(teacher.getComments().stream().mapToDouble(CommentDTO::getScore).sum() / teacher.getComments().size());
        teacherService.save(teacher);
        return finalComment;

    }

    @Override
    public Set<CommentDTO> getRequiredTeacherComments(String teacherId) {
        TeacherDTO teacher = teacherService.getTeacherById(Long.parseLong(teacherId));
        return teacher.getComments();
    }

    @Override
    public void deleteComment(long commentId, long teacherId) {
        TeacherDTO teacher = teacherService.getTeacherById(teacherId);
        teacher.getComments().removeIf(commentDTO -> commentDTO.getId().equals(commentId));
        teacherService.save(teacher);
    }
}
