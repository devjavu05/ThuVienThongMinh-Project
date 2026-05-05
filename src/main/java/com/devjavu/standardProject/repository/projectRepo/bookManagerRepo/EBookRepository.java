package com.devjavu.standardProject.repository.projectRepo.bookManagerRepo;

import com.devjavu.standardProject.entity.projectEntity.bookManager.EBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EBookRepository extends JpaRepository<EBook, String> {
}
