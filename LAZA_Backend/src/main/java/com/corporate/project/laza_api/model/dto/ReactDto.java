package com.corporate.project.laza_api.model.dto;
import org.springframework.hateoas.server.core.Relation;

@Relation(collectionRelation = "reacts",itemRelation = "react")
public record ReactDto(Long id,String name) {

}
