package com.Exam.ExamPortal.Repo;

import com.Exam.ExamPortal.Model.Exam.Question;
import com.Exam.ExamPortal.Model.Exam.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    Set<Question> findByQuiz(Quiz quiz);
}
