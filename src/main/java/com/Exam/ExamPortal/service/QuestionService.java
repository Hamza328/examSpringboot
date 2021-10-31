package com.Exam.ExamPortal.service;

import com.Exam.ExamPortal.Model.Exam.Question;
import com.Exam.ExamPortal.Model.Exam.Quiz;

import java.util.Set;

public interface QuestionService {

    public Question addQuestion(Question question);

    public Question updateQuestion(Question question);

    public Question getQuestion(Long quesId);

    public Set<Question> getQuestionOfQuiz(Quiz quiz);

    public void deleteQuestion(Long qId);

    public Question get(Long id);


}
