package com.spinyk.telegrambotcreator.da;

import com.spinyk.telegrambotcreator.game.simpleGame.SimpleGame;
import com.spinyk.telegrambotcreator.game.simpleGame.Step;

import java.util.HashMap;

public class SimpleDA {

    private HashMap storage;
    private HashMap<Long, Integer> progressStorage;

    public SimpleDA() {
        this.progressStorage = new HashMap();
        this.storage = new HashMap<>();
    }

    public HashMap getStorage() {
        return storage;
    }

    public Step nextStep(Long chatId) {
        progressStorage.put(chatId, progressStorage.get(chatId) + 1);
        return ((SimpleGame) storage.get("game")).getStep(progressStorage.get(chatId));
    }

    public Step getStep(Long chatId) {
        return ((SimpleGame) storage.get("game")).getStep(progressStorage.get(chatId));
    }

    public void addStep(String quesiton, String correctAnswer) {
        ((SimpleGame) storage.get("game")).addStep(quesiton, correctAnswer);
    }

    public boolean initializeSession(Long chatId) {
        if (!progressStorage.keySet().contains(chatId)) {
            progressStorage.put(chatId, 0);
            return true;
        }
        return false;
    }
}
