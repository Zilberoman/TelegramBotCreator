package com.spinyk.telegrambotcreator.bot;

import com.spinyk.telegrambotcreator.da.SimpleDA;
import com.spinyk.telegrambotcreator.game.simpleGame.SimpleGame;
import com.spinyk.telegrambotcreator.game.simpleGame.Step;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.util.Iterator;
import java.util.List;

public class Bot extends TelegramLongPollingBot {

    private SimpleDA simpleDA;

    public Bot(SimpleDA simpleDA) {
        super();
        this.simpleDA = simpleDA;
    }

    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            if(simpleDA.initializeSession(update.getMessage().getChatId())) {
                SendMessage message = new SendMessage()
                        .setChatId(update.getMessage().getChatId())
                        .setText("Это дефолтное приветственное сообщение бота");
                try {
                    sendMessage(message);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
            Step currentStep = simpleDA.getStep(update.getMessage().getChatId());
            if (currentStep.getCorrectAnswer().equals(update.getMessage().getText())) {
                currentStep = simpleDA.nextStep(update.getMessage().getChatId());
            }
            SendMessage message = new SendMessage()
                    .setChatId(update.getMessage().getChatId())
                    .setText(currentStep.getQuestion());
            try {
                sendMessage(message);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    public String getBotUsername() {
        return (String) simpleDA.getStorage().get("userName");
    }

    @Override
    public String getBotToken() {
        return (String) simpleDA.getStorage().get("token");
    }

}
