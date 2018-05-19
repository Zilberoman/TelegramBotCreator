package com.spinyk.telegrambotcreator.game.simpleGame;

public class Step {

    private final String question;
    private final String correctAnswer;

    public Step(String question, String correctAnswer) {
        this.question = question;
        this.correctAnswer = correctAnswer;
    }

    public String getQuestion() {
        return question;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }
}
