package com.corporate.project.laza_api.mapper;

import com.corporate.project.laza_api.model.dto.CreatePostDto;
import com.corporate.project.laza_api.model.dto.CreatePostTypeDto;
import com.corporate.project.laza_api.model.dto.PostDto;
import com.corporate.project.laza_api.model.dto.PostTypeDto;
import com.corporate.project.laza_api.model.entity.Post;
import com.corporate.project.laza_api.model.entity.PostType;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PostTypeMapper {

    List<PostTypeDto> mapPostTypeListToPostTypeDtoList(List<PostType> postTypeList);

    PostTypeDto mapPostTypeToPostTypeDto(PostType postType);

    PostType mapCreatePostTypeDtoToPostType(CreatePostTypeDto createPostTypeDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void mapForPartialUpdate(@MappingTarget PostType postType, CreatePostTypeDto createPostTypeDto);

}