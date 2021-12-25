package com.Xolotl.actionsInRows;

import com.Xolotl.backgroundActions.GenerateNewPassword;
import com.Xolotl.messagesender.MessageSender;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

public class RowsActions {
    private final MessageSender messageSender;

    public RowsActions(MessageSender sendMessage) {
        this.messageSender = sendMessage;
    }
    private String readMessage(Update update){
        Message readMessage = update.getMessage();
        if(readMessage.hasText()){
            String message = String.valueOf(readMessage.getText());
            return message;
        }
        else return null;
    }

    private  void waitForUpdate (Update update){
        int temp = update.getUpdateId();
        int temp2 = update.getUpdateId();
        readMessage(update);
        if (temp == temp2){
            synchronized (update){
            try {
                update.wait(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            waitForUpdate(update);
        }
        }
        else return;
    }

    public void row1Action(Message message, Update update) {
        message.getChatId();
        SendMessage sendMessage = new SendMessage();
        GenerateNewPassword generateNewPassword = new GenerateNewPassword();
        sendMessage.setChatId(String.valueOf(message.getChatId()));
        sendMessage.setText("Please enter your new password length");
        messageSender.sendMessage(sendMessage);
        Message readMessage = update.getMessage();
        waitForUpdate(update);
        int passLength = Integer.parseInt(readMessage(update));
        String pass = generateNewPassword.generatePassword(passLength); // pass - will be used for saving last password
        sendMessage.setText(pass);
        messageSender.sendMessage(sendMessage);
    }
}
