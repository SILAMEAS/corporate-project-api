package com.corporate.project.laza_api.assembler;

import com.corporate.project.laza_api.controller.PostTypeRestController;
import com.corporate.project.laza_api.controller.ReactRestController;
import com.corporate.project.laza_api.mapper.PostTypeMapper;
import com.corporate.project.laza_api.mapper.ReactMapper;
import com.corporate.project.laza_api.model.dto.PostTypeDto;
import com.corporate.project.laza_api.model.dto.ReactDto;
import com.corporate.project.laza_api.model.entity.PostType;
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
public class PostTypeModelAssembler extends RepresentationModelAssemblerSupport<PostType, EntityModel<PostTypeDto>> {
    private PostTypeMapper postTypeMapper;

    @SuppressWarnings("unchecked")
    public PostTypeModelAssembler() {
        super(ReactRestController.class,(Class<EntityModel<PostTypeDto>>)(Class<?>) EntityModel.class);
    }

    @Autowired
    public void setPostTypeMapper(PostTypeMapper postTypeMapper) {
        this.postTypeMapper = postTypeMapper;
    }

    @Override
    public EntityModel<PostTypeDto> toModel(PostType entity) {
        PostTypeDto postTypeDto = postTypeMapper.mapPostTypeToPostTypeDto(entity);
        Link selfLink = linkTo(methodOn(PostTypeRestController.class).getPostTypeById(entity.getId())).withSelfRel();
        Link collectionLink = linkTo(methodOn(PostTypeRestController.class).getAllPostType()).withRel(IanaLinkRelations.COLLECTION);
        return EntityModel.of(postTypeDto,selfLink,collectionLink);
    }

    @Override
    public CollectionModel<EntityModel<PostTypeDto>> toCollectionModel(Iterable<? extends PostType> entities) {
        return super.toCollectionModel(entities);
    }
}
