package com.devjavu.standardProject.entity.projectEntity.businessTransactions;

import com.devjavu.standardProject.entity.projectEntity.userProfiles.DocGia;
import com.devjavu.standardProject.entity.projectEntity.userProfiles.NhanVien;
import jakarta.persistence.*;
import lombok.*;
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
     LocalDate dueDate; // Hạn trả

    @ManyToOne
     DocGia nguoiMuon;

    @ManyToOne
     NhanVien nhanVien;

    @OneToMany(mappedBy = "phieuMuon")
     List<ChiTietPhieuMuon> details;
}
