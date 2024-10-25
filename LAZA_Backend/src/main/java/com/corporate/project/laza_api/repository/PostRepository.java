package com.corporate.project.laza_api.repository;

import com.corporate.project.laza_api.model.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
