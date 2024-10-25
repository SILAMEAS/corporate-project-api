package com.corporate.project.laza_api.service;

import com.corporate.project.laza_api.model.dto.CreatePostDto;
import com.corporate.project.laza_api.model.dto.CreatePostTypeDto;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;

public interface PostTypeService {
    CollectionModel<?> findAllPostTypes();
    EntityModel<?> findPostTypesById(Long id);
    Long createNewPostTypes(CreatePostTypeDto createPostTypeDto);
    Long updatePostTypesById(Long id, CreatePostTypeDto createPostTypeDto);
    void deletePostTypesById(Long id);
}
