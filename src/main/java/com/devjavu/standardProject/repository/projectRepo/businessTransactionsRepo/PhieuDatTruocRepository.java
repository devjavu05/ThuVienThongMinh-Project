package com.devjavu.standardProject.repository.projectRepo.businessTransactionsRepo;

import com.devjavu.standardProject.entity.projectEntity.bookManager.DauSach;
import com.devjavu.standardProject.entity.projectEntity.businessTransactions.PhieuDatTruoc;
import com.devjavu.standardProject.entity.projectEntity.userProfiles.DocGia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface PhieuDatTruocRepository extends JpaRepository<PhieuDatTruoc,String> {
    long countByDocGiaAndStatusIn(DocGia docGia, Collection<String> statuses);
    long countByDauSachAndStatusIn(DauSach dauSach, Collection<String> statuses);
    boolean existsByDocGiaAndDauSachAndStatusIn(DocGia docGia, DauSach dauSach, Collection<String> statuses);
    boolean existsByDauSachAndDocGia_IdNotAndStatusIn(DauSach dauSach, String docGiaId, Collection<String> statuses);
    List<PhieuDatTruoc> findAllByDauSachAndStatusInOrderByReservationDateAscIdAsc(DauSach dauSach, Collection<String> statuses);
    Optional<PhieuDatTruoc> findFirstByDauSachAndStatusInOrderByReservationDateAscIdAsc(DauSach dauSach, Collection<String> statuses);
    List<PhieuDatTruoc> findAllByDocGiaOrderByReservationDateDesc(DocGia docGia);
    List<PhieuDatTruoc> findAllByDauSach(DauSach dauSach);
}
