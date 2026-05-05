package com.devjavu.standardProject.repository.projectRepo.businessTransactionsRepo;

import com.devjavu.standardProject.entity.projectEntity.bookManager.EBook;
import com.devjavu.standardProject.entity.projectEntity.businessTransactions.PhieuMua;
import com.devjavu.standardProject.entity.projectEntity.userProfiles.DocGia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PhieuMuaRepository extends JpaRepository<PhieuMua, String> {
    long countByDocGiaAndPurchaseTimeBetween(DocGia docGia, LocalDateTime start, LocalDateTime end);
    boolean existsByDocGiaAndEBook(DocGia docGia, EBook eBook);
    List<PhieuMua> findAllByDocGiaOrderByPurchaseTimeDesc(DocGia docGia);
    List<PhieuMua> findAllByEBook(EBook eBook);
}
