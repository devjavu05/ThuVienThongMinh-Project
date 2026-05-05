package com.devjavu.standardProject.repository.projectRepo.userProfileRepo;

import com.devjavu.standardProject.entity.projectEntity.userProfiles.DocGia;
import com.devjavu.standardProject.entity.standardEntity.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocGiaRepository extends JpaRepository<DocGia,String> {
    DocGia findByEmail(String email);
    DocGia findByEmailIgnoreCase(String email);
    DocGia findByUser(User user);
}
