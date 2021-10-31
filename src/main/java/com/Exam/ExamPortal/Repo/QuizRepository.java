package com.Exam.ExamPortal.Repo;

import com.Exam.ExamPortal.Model.Exam.Category;
import com.Exam.ExamPortal.Model.Exam.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuizRepository extends JpaRepository<Quiz, Long> {
//    public void deleteByQid(Long id);
    List<Quiz> findByCategory(Category c);

    List<Quiz> findByActive(boolean b);
    List<Quiz> findByCategoryAndActive(Category c,boolean b);
}
