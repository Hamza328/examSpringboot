package com.Exam.ExamPortal.Repo;

import com.Exam.ExamPortal.Model.Exam.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByCid(Long id);
}