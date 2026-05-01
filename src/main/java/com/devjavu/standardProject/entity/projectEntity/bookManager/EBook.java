package com.devjavu.standardProject.entity.projectEntity.bookManager;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class EBook {
    @Id
     String id;
     String accessLink;
     String format;
     double fileSize;

    @OneToOne
    @MapsId
     DauSach dauSach;
}
