package com.devjavu.standardProject.repository.projectRepo.notificationRepo;

import com.devjavu.standardProject.entity.projectEntity.notification.ThongBao;
import com.devjavu.standardProject.entity.projectEntity.userProfiles.DocGia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ThongBaoRepository extends JpaRepository<ThongBao, String> {
    List<ThongBao> findAllByDocGiaOrderByCreatedAtDesc(DocGia docGia);
    Optional<ThongBao> findByIdAndDocGia(String id, DocGia docGia);
    boolean existsByDocGiaAndSourceKey(DocGia docGia, String sourceKey);
    long countByDocGiaAndReadFalse(DocGia docGia);
    List<ThongBao> findAllByDocGiaAndReadFalse(DocGia docGia);
}
