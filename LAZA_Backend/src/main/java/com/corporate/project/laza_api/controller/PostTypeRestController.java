package com.corporate.project.laza_api.controller;

import com.corporate.project.laza_api.model.apiresponse.ApiResponse;
import com.corporate.project.laza_api.model.dto.CreatePostTypeDto;
import com.corporate.project.laza_api.model.dto.CreateReactDto;
import com.corporate.project.laza_api.service.PostTypeService;
import com.corporate.project.laza_api.service.ReactService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/postType")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class PostTypeRestController {
    private final PostTypeService postTypeService;

    @GetMapping
    public ApiResponse<CollectionModel<?>> getAllPostType() {
        var postType = postTypeService.findAllPostTypes();
        System.out.println(postType);
        return new ApiResponse<>("success get postType", postType, 200);
    }

    @GetMapping("/{id}")
    public ApiResponse<EntityModel<?>> getPostTypeById(@PathVariable("id") Long id) {
        var postType = postTypeService.findPostTypesById(id);
        return new ApiResponse<>("success get postType", postType, 200);
    }


    @PostMapping("/create")
    public ApiResponse  addPostType(@RequestBody CreatePostTypeDto createPostTypeDto) {
        Long postTypesId =  postTypeService.createNewPostTypes(createPostTypeDto);
        var  postTypesCreated = postTypeService.findPostTypesById(postTypesId);
        return new ApiResponse<>("success create React", postTypesCreated, 200);
    }


    @PatchMapping("update/{id}")
    public  ApiResponse  updatePostTypeById(@PathVariable Long id, @RequestBody CreatePostTypeDto createPostTypeDto) {
        Long postTypeId =   postTypeService.updatePostTypesById(id,createPostTypeDto);
        var  postTypeUpdated = postTypeService.findPostTypesById(postTypeId);
        return new ApiResponse<>("success update postType", postTypeUpdated, 200);
    }


    @DeleteMapping("delete/{id}")
    public ApiResponse deletePostTypeById(@PathVariable Long id) {
        postTypeService.deletePostTypesById(id);
        return new ApiResponse<>("success delete postType", null, 200);
    }
}
