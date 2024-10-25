package com.corporate.project.laza_api.repository;

import com.corporate.project.laza_api.model.entity.PostType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostTypeRepository extends JpaRepository<PostType, Long> {
}
