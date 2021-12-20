package com.Xolotl.FirstBot;

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
    private String randomPass;
    private int passLength;

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

    private String generateRandomPassword() {
        // 33 126
        StringBuilder password = new StringBuilder();
        for (int i = 0; i < passLength; i++) {
            int charId = 33;
            int charShift = (int) (Math.random() * 126);
            password.append((char) (charShift + charId));
        }
        return password.toString();
    }

    private String readMessage(Update update) {
        Message readMessage = update.getMessage();
        if (readMessage.hasText()) {
            String message = String.valueOf(readMessage);
            return message;
        } else return null;
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
        Message message = update.getMessage();
        SendMessage sendMessage = new SendMessage();
        if (Objects.equals(readMessage(update), "/password")) {
            sendMessage("Please enter password length", update);
            passLength = Integer.parseInt(Objects.requireNonNull(readMessage(update)));
            sendMessage(String.valueOf(passLength), update);
            sendMessage(generateRandomPassword(), update);
        }
    }
}

