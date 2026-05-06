package com.devjavu.standardProject.repository.projectRepo.businessTransactionsRepo;

import com.devjavu.standardProject.entity.projectEntity.bookManager.EBook;
import com.devjavu.standardProject.entity.projectEntity.businessTransactions.PhieuMua;
import com.devjavu.standardProject.entity.projectEntity.userProfiles.DocGia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PhieuMuaRepository extends JpaRepository<PhieuMua, String> {
    long countByDocGiaAndPurchaseTimeBetween(DocGia docGia, LocalDateTime start, LocalDateTime end);

    @Query("SELECT COUNT(p) > 0 FROM PhieuMua p WHERE p.docGia = :docGia AND p.eBook = :eBook")
    boolean existsByDocGiaAndEBook(@Param("docGia") DocGia docGia, @Param("eBook") EBook eBook);

    List<PhieuMua> findAllByDocGiaOrderByPurchaseTimeDesc(DocGia docGia);

    @Query("SELECT p FROM PhieuMua p WHERE p.eBook = :eBook")
    List<PhieuMua> findAllByEBook(@Param("eBook") EBook eBook);

    long countByPurchaseTimeIsNotNull();
}
