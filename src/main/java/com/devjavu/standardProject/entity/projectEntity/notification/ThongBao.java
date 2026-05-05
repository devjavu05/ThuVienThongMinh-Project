package com.devjavu.standardProject.entity.projectEntity.notification;

import com.devjavu.standardProject.entity.projectEntity.userProfiles.DocGia;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Entity
@Table(name = "thong_bao")
@AllArgsConstructor
@NoArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class ThongBao {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    @Column(name = "title")
    String title;

    @Column(name = "content", columnDefinition = "TEXT")
    String content;

    @Column(name = "type")
    String type;

    @Column(name = "is_read")
    boolean read;

    @Column(name = "created_at")
    LocalDateTime createdAt;

    @Column(name = "source_key")
    String sourceKey;

    @ManyToOne
    @JoinColumn(name = "doc_gia_id")
    DocGia docGia;
}
