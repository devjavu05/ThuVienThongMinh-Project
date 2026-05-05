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
     String barcode;
     int stt;
     String location;
     String status;
     String physicalCondition;
     boolean isAvailable;

    @ManyToOne
     DauSach dauSach;
}
