package com.devjavu.standardProject.repository.projectRepo.businessTransactionsRepo;

import com.devjavu.standardProject.entity.projectEntity.bookManager.DauSach;
import com.devjavu.standardProject.entity.projectEntity.businessTransactions.ChiTietPhieuMuon;
import com.devjavu.standardProject.entity.projectEntity.businessTransactions.PhieuMuon;
import com.devjavu.standardProject.entity.projectEntity.userProfiles.DocGia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChiTietPhieuMuonRepository extends JpaRepository<ChiTietPhieuMuon,Long> {
    boolean existsByPhieuMuon_NguoiMuonAndCuonSach_DauSachAndStatusNot(DocGia docGia, DauSach dauSach, String status);
    List<ChiTietPhieuMuon> findAllByPhieuMuon_NguoiMuonOrderByPhieuMuon_BorrowDateDescIdDesc(DocGia docGia);
    List<ChiTietPhieuMuon> findAllByPhieuMuonOrderByIdAsc(PhieuMuon phieuMuon);
    List<ChiTietPhieuMuon> findAllByCuonSach_DauSach(DauSach dauSach);
    Optional<ChiTietPhieuMuon> findFirstByCuonSach_BarcodeAndReturnDateIsNullOrderByIdDesc(String barcode);
}
