package com.devjavu.standardProject.entity.projectEntity.bookManager;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class CuonSach {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
     String barcode;
     int stt;
     String location;
     String status;
     boolean isAvailable;

    @ManyToOne
     DauSach dauSach;
}
