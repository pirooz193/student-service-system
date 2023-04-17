package com.example.studentserviceapplication.service.mapper;

import com.example.studentserviceapplication.domain.Comment;
import com.example.studentserviceapplication.service.dto.CommentDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    Comment toEntity(CommentDTO commentDTO);

    CommentDTO toDTO(Comment comment);
}
