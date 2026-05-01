package com.devjavu.standardProject.repository.projectRepo.bookManagerRepo;

import com.devjavu.standardProject.entity.projectEntity.bookManager.DauSach;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface DauSachRepository extends JpaRepository<DauSach,String> {

    Optional<DauSach> findByTitle(String title);
}
