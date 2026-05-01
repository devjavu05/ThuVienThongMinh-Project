package com.devjavu.standardProject.repository.projectRepo.userProfileRepo;

import com.devjavu.standardProject.entity.projectEntity.userProfiles.NhanVien;
import com.devjavu.standardProject.entity.standardEntity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NhanVienRepository extends JpaRepository<NhanVien,String> {
    NhanVien findByUser(User user);
}
