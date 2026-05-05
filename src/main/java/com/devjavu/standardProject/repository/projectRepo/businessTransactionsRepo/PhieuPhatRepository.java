package com.devjavu.standardProject.repository.projectRepo.businessTransactionsRepo;

import com.devjavu.standardProject.entity.projectEntity.businessTransactions.PhieuPhat;
import com.devjavu.standardProject.entity.projectEntity.businessTransactions.PhieuMuon;
import com.devjavu.standardProject.entity.projectEntity.userProfiles.DocGia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhieuPhatRepository extends JpaRepository<PhieuPhat,String> {
    List<PhieuPhat> findAllByPhieuMuon_NguoiMuonOrderByIdDesc(DocGia docGia);
    List<PhieuPhat> findAllByPhieuMuon(PhieuMuon phieuMuon);
}
