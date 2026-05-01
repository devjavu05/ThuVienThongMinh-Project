package com.devjavu.standardProject.repository.standardRepo;

import com.devjavu.standardProject.entity.standardEntity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,String> {
}
