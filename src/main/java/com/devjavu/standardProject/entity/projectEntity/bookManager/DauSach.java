package com.devjavu.standardProject.entity.projectEntity.bookManager;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class DauSach {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
     String id;
     String title;
     String author;
     String category;
     String description;
     @Column(columnDefinition = "LONGTEXT")
     String longIntroduction;
     Integer floorNumber;
     String shelfCode;
     Integer publishYear;
     String coverImageUrl;
     @Builder.Default
     Double averageRating = 0.0;
     LocalDateTime createdAt;
     int quantity;

    @OneToMany(mappedBy = "dauSach")
     List<CuonSach> cuonSaches;
}
