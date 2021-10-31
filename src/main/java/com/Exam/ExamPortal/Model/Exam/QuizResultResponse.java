package com.Exam.ExamPortal.Model.Exam;

public class QuizResultResponse {

    double marksgot;
    int correctAnswer;
    int attempted;

    public QuizResultResponse() {
    }

    public double getMarksgot() {
        return marksgot;
    }

    public void setMarksgot(double marksgot) {
        this.marksgot = marksgot;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(int correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public int getAttempted() {
        return attempted;
    }

    public void setAttempted(int attempted) {
        this.attempted = attempted;
    }
}
