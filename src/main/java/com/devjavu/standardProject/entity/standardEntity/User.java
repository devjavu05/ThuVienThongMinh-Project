package com.devjavu.standardProject.entity.standardEntity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;
    String username;
    String password;
    @Builder.Default
    String status = "ACTIVE";
    @Builder.Default
    int failedLoginAttempts = 0;
    LocalDateTime lockedUntil;

    @ManyToMany
    Set<Role> roles;

}
