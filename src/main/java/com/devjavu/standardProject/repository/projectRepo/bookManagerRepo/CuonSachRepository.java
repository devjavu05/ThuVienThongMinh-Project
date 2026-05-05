package com.devjavu.standardProject.repository.projectRepo.bookManagerRepo;

import com.devjavu.standardProject.entity.projectEntity.bookManager.CuonSach;
import com.devjavu.standardProject.entity.projectEntity.bookManager.DauSach;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CuonSachRepository extends JpaRepository<CuonSach,String> {
    List<CuonSach> findAllByDauSach(DauSach dauSach);
    List<CuonSach> findAllByBarcodeStartingWith(String prefix);
}
