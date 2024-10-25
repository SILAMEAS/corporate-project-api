package com.corporate.project.laza_api.service;

import com.corporate.project.laza_api.model.dto.CreateReactDto;
import com.corporate.project.laza_api.model.dto.ReactDto;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;

public interface ReactService {
    CollectionModel<?> findAllReact();
    EntityModel<?> findReactById(Long id);
    Long createNewReact(CreateReactDto createReactDto);
    Long updateReactById(Long id, CreateReactDto createReactDto);
    void deleteReactById(Long id);
}
