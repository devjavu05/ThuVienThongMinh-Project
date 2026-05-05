package com.devjavu.standardProject.entity.projectEntity.userProfiles;

import com.devjavu.standardProject.entity.standardEntity.User;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class DocGia {
    @Id
     @GeneratedValue(strategy = GenerationType.UUID)
     String id;
     String fullName;
     String email;
     String cardType;
     @Builder.Default
     double balance = 0;
     int totalFines;

    @OneToOne
     User user;
}
