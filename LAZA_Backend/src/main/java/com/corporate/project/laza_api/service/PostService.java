package com.corporate.project.laza_api.service;

import com.corporate.project.laza_api.model.dto.CreatePostDto;
import com.corporate.project.laza_api.model.dto.CreateReactDto;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;

public interface PostService{
    CollectionModel<?> findAllPost();
    EntityModel<?> findPostById(Long id);
    Long createNewPost(CreatePostDto createPostDto);
    Long updatePostById(Long id, CreatePostDto createPostDto);
    void deletePostById(Long id);
}
