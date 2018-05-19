package com.spinyk.telegrambotcreator.rest;

import com.spinyk.telegrambotcreator.bot.Bot;
import com.spinyk.telegrambotcreator.da.SimpleDA;
import com.spinyk.telegrambotcreator.game.simpleGame.SimpleGame;
import com.spinyk.telegrambotcreator.game.simpleGame.Step;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.Map;

@RestController
public class SimpleGameController {

    private SimpleDA simpleDA = new SimpleDA();

    @RequestMapping("/createSimpleGame")
    public void createSimpleGame(@RequestParam(value="token", required = false, defaultValue = "334849532:AAFY1QtxcDj5o0EJCPVJxlpzKonprkvIRp8") String token,
                                 @RequestParam(value="userName", required = false, defaultValue = "SpinykBot") String userName) {
        System.out.println("Игра создана");

        simpleDA.getStorage().put("token", token);
        simpleDA.getStorage().put("userName", userName);
        simpleDA.getStorage().put("game", new SimpleGame(new ArrayList<>()));

        ApiContextInitializer.init();

        TelegramBotsApi botsApi = new TelegramBotsApi();

        try {
            botsApi.registerBot(new Bot(simpleDA));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/addStep")
    public void addStep(@RequestParam(value="question") String question,
                        @RequestParam(value="correctAnswer") String correctAnswer) {
        System.out.println("Шаг игры добавлен");
        simpleDA.addStep(question, correctAnswer);
    }



}
