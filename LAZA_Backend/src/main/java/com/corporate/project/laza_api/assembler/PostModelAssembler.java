package com.corporate.project.laza_api.assembler;

import com.corporate.project.laza_api.controller.PostRestController;
import com.corporate.project.laza_api.controller.ReactRestController;
import com.corporate.project.laza_api.mapper.PostMapper;
import com.corporate.project.laza_api.mapper.ReactMapper;
import com.corporate.project.laza_api.model.dto.PostDto;
import com.corporate.project.laza_api.model.dto.ReactDto;
import com.corporate.project.laza_api.model.entity.Post;
import com.corporate.project.laza_api.model.entity.React;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
@Component
public class PostModelAssembler extends RepresentationModelAssemblerSupport<Post, EntityModel<PostDto>> {


    private PostMapper postMapper;

    @SuppressWarnings("unchecked")
    public PostModelAssembler() {
        super(ReactRestController.class,(Class<EntityModel<PostDto>>)(Class<?>) EntityModel.class);
    }

    @Autowired
    public void setPostMapper(PostMapper postMapper) {
        this.postMapper = postMapper;
    }

    @Override
    public EntityModel<PostDto> toModel(Post entity) {
        PostDto postDto = postMapper.mapPostToPostDto(entity);
        Link selfLink = linkTo(methodOn(PostRestController.class).getPostById(entity.getId())).withSelfRel();
        Link collectionLink = linkTo(methodOn(PostRestController.class).getAllPost()).withRel(IanaLinkRelations.COLLECTION);
        return EntityModel.of(postDto,selfLink,collectionLink);
    }

    @Override
    public CollectionModel<EntityModel<PostDto>> toCollectionModel(Iterable<? extends Post> entities) {
        return super.toCollectionModel(entities);
    }
}
