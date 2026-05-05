package com.devjavu.standardProject.entity.projectEntity.businessTransactions;

import com.devjavu.standardProject.entity.projectEntity.bookManager.DauSach;
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
public class DanhGia {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;
    int rating;
    String comment;
    LocalDateTime updatedAt;

    @ManyToOne
    DocGia docGia;

    @ManyToOne
    DauSach dauSach;
}
