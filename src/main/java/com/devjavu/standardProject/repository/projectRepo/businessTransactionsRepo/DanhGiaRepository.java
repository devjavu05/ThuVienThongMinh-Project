package com.devjavu.standardProject.repository.projectRepo.businessTransactionsRepo;

import com.devjavu.standardProject.entity.projectEntity.bookManager.DauSach;
import com.devjavu.standardProject.entity.projectEntity.businessTransactions.DanhGia;
import com.devjavu.standardProject.entity.projectEntity.userProfiles.DocGia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DanhGiaRepository extends JpaRepository<DanhGia, String> {
    Optional<DanhGia> findByDocGiaAndDauSach(DocGia docGia, DauSach dauSach);
    List<DanhGia> findAllByDauSachOrderByUpdatedAtDesc(DauSach dauSach);
    List<DanhGia> findAllByDauSach(DauSach dauSach);
}
