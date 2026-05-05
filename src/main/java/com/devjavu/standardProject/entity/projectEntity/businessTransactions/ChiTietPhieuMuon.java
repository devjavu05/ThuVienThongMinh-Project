package com.devjavu.standardProject.entity.projectEntity.businessTransactions;

import com.devjavu.standardProject.entity.projectEntity.bookManager.CuonSach;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class ChiTietPhieuMuon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     Long id;
     LocalDate returnDate;
     LocalDate dueDate;
     String status;
     @Builder.Default
     int renewalCount = 0;

    @ManyToOne
     PhieuMuon phieuMuon;

    @ManyToOne
     CuonSach cuonSach;
}
