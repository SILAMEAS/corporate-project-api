package com.corporate.project.laza_api.repository;

import com.corporate.project.laza_api.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Long> {
    List<Role> findAllByNameIn(List<String> roleNames);
}

