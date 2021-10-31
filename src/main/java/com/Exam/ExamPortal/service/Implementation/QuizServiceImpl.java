package com.Exam.ExamPortal.service.Implementation;

import com.Exam.ExamPortal.Model.Exam.Category;
import com.Exam.ExamPortal.Model.Exam.Quiz;
import com.Exam.ExamPortal.Repo.QuizRepository;
import com.Exam.ExamPortal.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class QuizServiceImpl implements QuizService {

    @Autowired
    QuizRepository quizRepo;

    @Override
    public Quiz addQuiz(Quiz quiz) {
        return this.quizRepo.save(quiz);
    }

    @Override
    public Quiz updateQuiz(Quiz quiz) {
        return this.quizRepo.save(quiz);
    }

    @Override
    public Set<Quiz> getQuizes() {
        return new HashSet<>(this.quizRepo.findAll());
    }

    @Override
    public Quiz getQuiz(Long quizId) {
        return this.quizRepo.findById(quizId).get();
    }


    @Override
    public void deleteQuiz(Long quizId) {
//         Quiz quiz =this.quizRepo.findById(quizId).get();
        System.out.println(quizId);
        this.quizRepo.deleteById(quizId);
    }

    @Override
    public List<Quiz> getQuizzesofCategory(Category c) {
        return this.quizRepo.findByCategory(c);
    }

    @Override
    public List<Quiz> getActiveQuizes() {
        return this.quizRepo.findByActive(true);
    }

    @Override
    public List<Quiz> getActiveQuizesOfCategory(Category c) {
        return this.quizRepo.findByCategoryAndActive(c,true);
    }
}
