package com.corporate.project.laza_api.service.serviceImp;

import com.corporate.project.laza_api.assembler.PostTypeModelAssembler;
import com.corporate.project.laza_api.mapper.PostTypeMapper;
import com.corporate.project.laza_api.model.dto.CreatePostTypeDto;
import com.corporate.project.laza_api.model.entity.PostType;
import com.corporate.project.laza_api.model.entity.React;
import com.corporate.project.laza_api.repository.PostTypeRepository;
import com.corporate.project.laza_api.service.PostTypeService;
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
public class PostTypeServiceImp implements PostTypeService {

    private final PostTypeRepository postTypeRepository;
    private final PostTypeModelAssembler postTypeModelAssembler;
    private final PostTypeMapper postTypeMapper;
    @Override
    public CollectionModel<?> findAllPostTypes() {
        List<PostType> postTypes = postTypeRepository.findAll();
        return postTypeModelAssembler.toCollectionModel(postTypes);
    }

    @Override
    public EntityModel<?> findPostTypesById(Long id) {
        Optional<PostType> postType = postTypeRepository.findById(id);
        return  postTypeModelAssembler.toModel(postType.orElseThrow());
    }

    @Override
    public Long createNewPostTypes(CreatePostTypeDto createPostTypeDto) {
        PostType postType = postTypeMapper.mapCreatePostTypeDtoToPostType(createPostTypeDto);
        postTypeRepository.save(postType);
        System.out.println(postType);
        return postType.getId();
    }

    @Override
    public Long updatePostTypesById(Long id, CreatePostTypeDto createPostTypeDto) {
        PostType postType = postTypeRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"PostType not found"));
        postTypeMapper.mapForPartialUpdate(postType, createPostTypeDto);
        postTypeRepository.save(postType);
        return postType.getId();
    }

    @Override
    public void deletePostTypesById(Long id) {
        postTypeRepository.deleteById(id);
    }
}
