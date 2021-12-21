package com.Xolotl.FirstBot;


import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class MessageSender{
    private HelloWorldBot helloWorldBot;

    void sendMessage(SendMessage sendMessage){
        try {
            helloWorldBot.execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void setHelloWorldBot(HelloWorldBot helloWorldBot) {
        this.helloWorldBot = helloWorldBot;
    }
}
