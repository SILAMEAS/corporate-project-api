package com.corporate.project.laza_api.model.dto;

import org.springframework.hateoas.server.core.Relation;

@Relation(collectionRelation = "posts",itemRelation = "post ")
public record PostDto(Long id ,String title , String content,String description,Long userId,Long postType) {
}
