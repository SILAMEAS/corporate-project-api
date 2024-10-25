package com.corporate.project.laza_api.mapper;

import com.corporate.project.laza_api.model.dto.CreatePostDto;
import com.corporate.project.laza_api.model.dto.CreateReactDto;
import com.corporate.project.laza_api.model.dto.PostDto;
import com.corporate.project.laza_api.model.dto.ReactDto;
import com.corporate.project.laza_api.model.entity.Post;
import com.corporate.project.laza_api.model.entity.React;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PostMapper {

    List<PostDto> mapPostListToPostDtoList(List<Post> postList);

    PostDto mapPostToPostDto(Post post);

    Post mapCreatePostDtoToPost(CreatePostDto createPostDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void mapForPartialUpdate(@MappingTarget Post post, CreatePostDto createPostDto);




}
