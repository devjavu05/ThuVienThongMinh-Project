package com.devjavu.standardProject.entity.projectEntity.businessTransactions;

import com.devjavu.standardProject.entity.projectEntity.userProfiles.DocGia;
import com.devjavu.standardProject.entity.projectEntity.userProfiles.NhanVien;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class PhieuMuon {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    LocalDate borrowDate;
    LocalDate dueDate;

    @Builder.Default
    boolean editable = true;

    @Builder.Default
    int renewalCount = 0;

    @ManyToOne
    DocGia nguoiMuon;

    @ManyToOne
    NhanVien nhanVien;

    @OneToMany(mappedBy = "phieuMuon")
    List<ChiTietPhieuMuon> details;
}
