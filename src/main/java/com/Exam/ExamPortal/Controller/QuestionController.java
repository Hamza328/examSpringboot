package com.Exam.ExamPortal.Controller;

import com.Exam.ExamPortal.Model.Exam.Question;
import com.Exam.ExamPortal.Model.Exam.Quiz;
import com.Exam.ExamPortal.Model.Exam.QuizResultResponse;
import com.Exam.ExamPortal.service.QuestionService;
import com.Exam.ExamPortal.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
@RequestMapping("/question")
@CrossOrigin("*")
public class QuestionController {

    @Autowired
    QuestionService qservice;

    @Autowired
    QuizService quizService;

    @PostMapping("/")
    public ResponseEntity<?> addQuestion(@RequestBody Question question){
        System.out.println(question);
        return  ResponseEntity.ok(this.qservice.addQuestion(question));
    }

    @GetMapping("/{quesId}")
    public ResponseEntity<?> getQuestion(@PathVariable("quesId") Long quesId){
        return ResponseEntity.ok(this.qservice.getQuestion(quesId));
    }

    @GetMapping("/quiz/all/{qid}")
    public ResponseEntity<?> getQuestionsOfQuizAdmin(@PathVariable("qid") Long qid){
       Quiz quiz =this.quizService.getQuiz(qid);
       Set<Question> QuestionOfQuiz= this.qservice.getQuestionOfQuiz(quiz);
       return ResponseEntity.ok(QuestionOfQuiz);

    }

    @GetMapping("/quiz/{qid}")
    public ResponseEntity<?> getQuestionsOfQuiz(@PathVariable("qid") Long qid){
        Quiz quiz =this.quizService.getQuiz(qid);
        List<Question> list =new ArrayList(quiz.getQuestions());
        if(list.size() > Integer.parseInt(quiz.getNumberOfQuestions())){
            list =list.subList(0,Integer.parseInt(quiz.getNumberOfQuestions()+1));
        }
        list.forEach(q->{
            q.setAnswer("");
        });
        Collections.shuffle(list);
        return ResponseEntity.ok(list);
    }

    @PutMapping("/")
    public ResponseEntity<?> updateQuestion(@RequestBody Question question){
        return ResponseEntity.ok(this.qservice.updateQuestion(question));
    }

    @DeleteMapping("/{quesId}")
    public void delete(@PathVariable("quesId") Long quesId){
        this.qservice.deleteQuestion(quesId);
    }

    @PostMapping("/eval-quiz")
    public ResponseEntity<?> EvalQuiz(@RequestBody List<Question> questions){
        double marksgot =0;
        int correctAnswer=0;
        int attempted =0;

        for (Question q:questions){
            Question ques =this.qservice.get(q.getQuesId());
            if(ques.getAnswer().equals(q.getGivenAnswer())){
                correctAnswer++;

                double marksingle = Double.parseDouble(questions.get(0).getQuiz().getMaxMarks())/questions.size();
                marksgot +=marksingle;
            }
            if(q.getGivenAnswer()!=null){
               attempted++;
            }
        }
        QuizResultResponse result =new QuizResultResponse();
        result.setMarksgot(marksgot);
        result.setCorrectAnswer(correctAnswer);
        result.setAttempted(attempted);

//        Map<String ,Object> map =Map.of("marksgot",marksgot,"correctAnswer",correctAnswer,"attempted",attempted);
        return ResponseEntity.ok(result);
    }


}
