package com.corporate.project.laza_api.repository;

import com.corporate.project.laza_api.model.entity.Reaction;
import org.mapstruct.Mapper;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ReactionRepository extends JpaRepository<Reaction, Long> {

}
