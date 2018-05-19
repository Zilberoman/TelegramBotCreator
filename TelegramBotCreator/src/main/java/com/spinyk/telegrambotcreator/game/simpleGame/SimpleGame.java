package com.spinyk.telegrambotcreator.game.simpleGame;

import com.spinyk.telegrambotcreator.game.Node;
import com.sun.research.ws.wadl.Link;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Должен запускаться в одном потоке под каждую сессию
 */
public class SimpleGame {

    private List<Step> game;

    public SimpleGame(List<Step> game) {
        this.game = game;
    }

    public Step getStep(Integer index) {
        try {
            return game.get(index);
        } catch (IndexOutOfBoundsException e) {
            return new Step("Спасибо! Вы ответили на все вопросы.", "");
        }
    }

    public void addStep(String quesiton, String correctAnswer) {
        game.add(new Step(quesiton, correctAnswer));
    }
}
