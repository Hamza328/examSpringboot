package com.Exam.ExamPortal.Controller;

import com.Exam.ExamPortal.Model.Exam.Category;
import com.Exam.ExamPortal.Model.Exam.Quiz;
import com.Exam.ExamPortal.service.CategoryService;
import com.Exam.ExamPortal.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz")
@CrossOrigin("*")
public class QuizController {

    @Autowired
    QuizService quizService;

    @Autowired
    CategoryService categoryService;

    @PostMapping("/")
    public ResponseEntity<?> addQuiz(@RequestBody Quiz quiz){
        return  ResponseEntity.ok(this.quizService.addQuiz(quiz));
    }

    @GetMapping("/{quizId}")
    public Quiz getQuiz(@PathVariable("quizId") Long quizId){
        return  this.quizService.getQuiz(quizId);
    }

    @GetMapping("/")
    public ResponseEntity<?> getQuizzes(){
        return ResponseEntity.ok(this.quizService.getQuizes());
    }

    @PutMapping("/")
    public Quiz updateQuiz(@RequestBody Quiz quiz){

        Quiz quize=this.quizService.updateQuiz(quiz);
        System.out.println(quize);
        return quize;
    }

    @DeleteMapping("/{quizId}")
    public void  deleteQui(@PathVariable("quizId") Long quizId){
        this.quizService.deleteQuiz(quizId);
    }

    @GetMapping("/category/{id}")
    public List<Quiz> getQuizOfcategory(@PathVariable("id") Long id){
        Category c= this.categoryService.getCategory(id);
        return   this.quizService.getQuizzesofCategory(c);
    }

    @GetMapping("/active")
    public List<Quiz> getActivequizes(){
        return this.quizService.getActiveQuizes();
    }

    @GetMapping("/category/active/{cid}")
    public List<Quiz> getActivequizesofCategory(@PathVariable("cid") Long cid){
        
        Category c= this.categoryService.getCategory(cid);
        return this.quizService.getActiveQuizesOfCategory(c);
    }

}
