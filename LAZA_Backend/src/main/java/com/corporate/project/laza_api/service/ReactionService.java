package com.corporate.project.laza_api.service;

import com.corporate.project.laza_api.model.dto.CreateReactDto;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;

public interface ReactionService {
    CollectionModel<?> findAllReaction();
    EntityModel<?> findReactionById(Long id);
    Long createNewReaction(CreateReactionDto createReactionDto);
    Long updateReactionById(Long id, CreateReactionDto createReactionDto);
    void deleteReactionById(Long id);
}
