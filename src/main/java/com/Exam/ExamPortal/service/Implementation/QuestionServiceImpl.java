package com.Exam.ExamPortal.service.Implementation;

import com.Exam.ExamPortal.Model.Exam.Question;
import com.Exam.ExamPortal.Model.Exam.Quiz;
import com.Exam.ExamPortal.Repo.QuestionRepository;
import com.Exam.ExamPortal.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    QuestionRepository questionRepo;

    @Override
    public Question addQuestion(Question question) {
        return this.questionRepo.save(question);
    }

    @Override
    public Question updateQuestion(Question question) {
        return this.questionRepo.save(question);
    }

    @Override
    public Question getQuestion(Long quesId) {
        return this.questionRepo.findById(quesId).get();
    }

    @Override
    public Set<Question> getQuestionOfQuiz(Quiz quiz) {
        return this.questionRepo.findByQuiz(quiz);
    }

    @Override
    public void deleteQuestion(Long qId) {
       this.questionRepo.deleteById(qId);
    }

    @Override
    public Question get(Long id) {
        return this.questionRepo.getById(id);
    }
}
