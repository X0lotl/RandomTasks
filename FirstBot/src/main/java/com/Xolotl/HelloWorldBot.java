package com.Xolotl;

import com.Xolotl.services.SendMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class HelloWorldBot extends TelegramLongPollingBot {
    @Value("${telegram.bot.username}")
    private String username;
    @Value("${telegram.bot.token}")
    private String token;
    private SendMessageService sendMessageService;

    @Override
    public String getBotUsername() {
        return username;
    }

    @Override
    public String getBotToken() {
        return token;
    }

    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        sendMessageService.keyBord(message, update);

    }

    @Autowired
    public void setSendMessageService(SendMessageService sendMessageService) {
        this.sendMessageService = sendMessageService;
    }
}