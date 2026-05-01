package com.devjavu.standardProject.entity.projectEntity.bookManager;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

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
     int quantity;

    @OneToMany(mappedBy = "dauSach")
     List<CuonSach> cuonSaches;
}
