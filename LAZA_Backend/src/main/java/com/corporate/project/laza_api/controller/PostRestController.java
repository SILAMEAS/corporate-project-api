package com.corporate.project.laza_api.controller;


import com.corporate.project.laza_api.model.apiresponse.ApiResponse;
import com.corporate.project.laza_api.model.dto.CreatePostDto;
import com.corporate.project.laza_api.model.dto.CreateReactDto;
import com.corporate.project.laza_api.service.PostService;
import com.corporate.project.laza_api.service.ReactService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class PostRestController {


    private final PostService postService;

    @GetMapping
    public ApiResponse<CollectionModel<?>> getAllPost() {
        var posts = postService.findAllPost();
        System.out.println(posts);
        return new ApiResponse<>("success get Post", posts, 200);
    }

    @GetMapping("/{id}")
    public ApiResponse<EntityModel<?>> getPostById(@PathVariable("id") Long id) {
        var post = postService.findPostById(id);
        return new ApiResponse<>("success get Post", post, 200);
    }


    @PostMapping("/create")
    public ApiResponse  addPost(@RequestBody CreatePostDto createPostDto) {
        Long postId =  postService.createNewPost(createPostDto);
        var  postCreated = postService.findPostById(postId);
        return new ApiResponse<>("success create Post", postCreated, 200);
    }


    @PatchMapping("update/{id}")
    public  ApiResponse  updatePostById(@PathVariable Long id, @RequestBody CreatePostDto createPostDto) {
        Long postId =   postService.updatePostById(id,createPostDto);
        var  postUpdated = postService.findPostById(postId);
        return new ApiResponse<>("success update Post",postUpdated , 200);
    }


    @DeleteMapping("delete/{id}")
    public ApiResponse deletePostById(@PathVariable Long id) {
        postService.deletePostById(id);
        return new ApiResponse<>("success delete Post", null, 200);
    }
}
