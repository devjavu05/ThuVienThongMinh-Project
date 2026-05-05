package com.devjavu.standardProject.entity.projectEntity.businessTransactions;

import com.devjavu.standardProject.entity.projectEntity.bookManager.EBook;
import com.devjavu.standardProject.entity.projectEntity.userProfiles.DocGia;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PhieuMua {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;
    LocalDateTime purchaseTime;
    double amount;
    String accessLink;

    @ManyToOne
    DocGia docGia;

    @ManyToOne
    EBook eBook;
}
