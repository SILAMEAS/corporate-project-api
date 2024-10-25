package com.corporate.project.laza_api.service.serviceImp;

import com.corporate.project.laza_api.assembler.PostModelAssembler;
import com.corporate.project.laza_api.assembler.ReactModelAssembler;
import com.corporate.project.laza_api.mapper.PostMapper;
import com.corporate.project.laza_api.mapper.ReactMapper;
import com.corporate.project.laza_api.model.dto.CreatePostDto;
import com.corporate.project.laza_api.model.entity.Post;
import com.corporate.project.laza_api.model.entity.React;
import com.corporate.project.laza_api.repository.PostRepository;
import com.corporate.project.laza_api.repository.ReactRepository;
import com.corporate.project.laza_api.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostServiceImp implements PostService {

    private final PostRepository postRepository;
    private final PostMapper postMapper;
    private final PostModelAssembler postModelAssembler;




    @Override
    public CollectionModel<?> findAllPost() {
        List<Post> posts = postRepository.findAll();
        return postModelAssembler.toCollectionModel(posts);

    }

    @Override
    public EntityModel<?> findPostById(Long id) {
        Optional<Post> post = postRepository.findById(id);
        return  postModelAssembler.toModel(post.orElseThrow());
    }

    @Override
    public Long createNewPost(CreatePostDto createPostDto) {
        Post post = postMapper.mapCreatePostDtoToPost(createPostDto);
        postRepository.save(post);
        System.out.println(post);
        return post.getId();
    }

    @Override
    public Long updatePostById(Long id, CreatePostDto createPostDto) {
        Post post = postRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Post not found"));
        postMapper.mapForPartialUpdate(post, createPostDto);
        postRepository.save(post);
        return post.getId();
    }

    @Override
    public void deletePostById(Long id) {
        postRepository.deleteById(id);
    }
}
