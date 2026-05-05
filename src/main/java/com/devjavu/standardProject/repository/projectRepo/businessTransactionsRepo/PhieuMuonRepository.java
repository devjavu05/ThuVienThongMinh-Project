package com.devjavu.standardProject.repository.projectRepo.businessTransactionsRepo;

import com.devjavu.standardProject.entity.projectEntity.businessTransactions.PhieuMuon;
import com.devjavu.standardProject.entity.projectEntity.userProfiles.DocGia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhieuMuonRepository extends JpaRepository<PhieuMuon,String> {
    List<PhieuMuon> findAllByNguoiMuonOrderByBorrowDateDesc(DocGia docGia);
}
