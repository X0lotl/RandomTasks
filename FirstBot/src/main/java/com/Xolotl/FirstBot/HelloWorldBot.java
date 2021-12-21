package com.Xolotl.FirstBot;

import com.Xolotl.FirstBot.services.SendMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Objects;

@Component
public class HelloWorldBot extends TelegramLongPollingBot {
    @Value("${telegram.bot.username}")
    private String username;
    @Value("${telegram.bot.token}")
    private String token;
    private SendMessageService sendMessageService;

    public HelloWorldBot() {
    }

    @Override
    public String getBotUsername() {
        return username;
    }

    @Override
    public String getBotToken() {
        return token;
    }

    private String generateRandomPassword(int passLength) {
        // 33 126
        StringBuilder password = new StringBuilder();
        for (int i = 0; i < passLength; i++) {
            password.append((char) ((Math.random() * 93) + 33));
        }
        return password.toString();
    }

    private String readMessage(Update update) {
        Message readMessage = update.getMessage();
        String message = String.valueOf(readMessage.getText());
        return message;
    }

    private void sendMessage(String outputMessage, Update update) {
        Message message = update.getMessage();
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText(outputMessage);
        sendMessage.setChatId(String.valueOf(message.getChatId()));
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpdateReceived(Update update) {
        readMessage(update);
        if(update.hasMessage()){
            Message message = new Message();
            if (message.hasText()){
                sendMessageService.defaultMessage(message);
            }
        }
    }

    @Autowired
    public void setSendMessageService(SendMessageService sendMessageService) {
        this.sendMessageService = sendMessageService;
    }
}

