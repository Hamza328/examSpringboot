package com.Exam.ExamPortal.service;

import com.Exam.ExamPortal.Model.Exam.Category;
import com.Exam.ExamPortal.Model.Exam.Quiz;

import java.util.List;
import java.util.Set;

public interface QuizService {

    public Quiz addQuiz(Quiz quiz);

    public Quiz updateQuiz(Quiz quiz);

    public Set<Quiz> getQuizes();

    public Quiz getQuiz(Long quizId);

    public void deleteQuiz(Long quizId);

    List<Quiz> getQuizzesofCategory(Category c);

    List<Quiz> getActiveQuizes();

    List<Quiz> getActiveQuizesOfCategory(Category c);
}
