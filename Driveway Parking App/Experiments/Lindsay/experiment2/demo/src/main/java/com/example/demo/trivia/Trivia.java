package com.example.demo.trivia;

public class Trivia {

    private String question;

    private String answer;

    public Trivia() {

    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        return "Question: " + question + " \n" +
                "Answer: " + answer;
    }
}
