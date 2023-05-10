package com.example.studentserviceapplication.service.mapper;

import com.example.studentserviceapplication.domain.Comment;
import com.example.studentserviceapplication.service.dto.CommentDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    Comment toEntity(CommentDTO commentDTO);

    @Mapping(source = "user", target = "user")
    CommentDTO toDTO(Comment comment);
}
