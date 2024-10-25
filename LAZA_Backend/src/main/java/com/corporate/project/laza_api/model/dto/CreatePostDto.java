package com.corporate.project.laza_api.model.dto;

public record CreatePostDto (String title , String content,String description,Long userId,Long postType){
}
