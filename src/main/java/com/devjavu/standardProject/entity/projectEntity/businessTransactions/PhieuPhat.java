package com.devjavu.standardProject.entity.projectEntity.businessTransactions;

import com.devjavu.standardProject.entity.projectEntity.bookManager.CuonSach;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class PhieuPhat {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
     String id;
     double amount;
     String reason;
     boolean paid;

    @OneToOne
     PhieuMuon phieuMuon;
    @OneToOne
     CuonSach cuonSach;
}
