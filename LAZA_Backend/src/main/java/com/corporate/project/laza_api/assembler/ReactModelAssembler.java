package com.corporate.project.laza_api.assembler;

import com.corporate.project.laza_api.controller.ReactRestController;
import com.corporate.project.laza_api.mapper.ReactMapper;
import com.corporate.project.laza_api.model.dto.ReactDto;
import com.corporate.project.laza_api.model.entity.React;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import java.util.Collection;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ReactModelAssembler extends RepresentationModelAssemblerSupport<React, EntityModel<ReactDto>> {
    private ReactMapper reactMapper;

    @SuppressWarnings("unchecked")
    public ReactModelAssembler() {
    super(ReactRestController.class,(Class<EntityModel<ReactDto>>)(Class<?>) EntityModel.class);
    }

    @Autowired
    public void setReactMapper(ReactMapper reactMapper) {
        this.reactMapper = reactMapper;
    }

    @Override
    public EntityModel<ReactDto> toModel(React entity) {
        ReactDto reactDto = reactMapper.mapReactToReactDto(entity);
        Link  selfLink = linkTo(methodOn(ReactRestController.class).getReactById(entity.getId())).withSelfRel();
        Link collectionLink = linkTo(methodOn(ReactRestController.class).getAllReact()).withRel(IanaLinkRelations.COLLECTION);
    return EntityModel.of(reactDto,selfLink,collectionLink);
    }

    @Override
    public CollectionModel<EntityModel<ReactDto>> toCollectionModel(Iterable<? extends React> entities) {
        return super.toCollectionModel(entities);
    }
}
