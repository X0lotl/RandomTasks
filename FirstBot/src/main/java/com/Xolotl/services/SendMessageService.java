package com.Xolotl.services;


import com.Xolotl.HelloWorldBot;
import com.Xolotl.actionsInRows.RowsActions;
import com.Xolotl.backgroundActions.GenerateNewPassword;
import com.Xolotl.domain.BotUser;
import com.Xolotl.messagesender.MessageSender;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;


import java.util.ArrayList;

@Service
public class SendMessageService {
    public boolean row1Action;
    private Object GenerateNewPassword;

    private void initAction(){

    }
    //private Chache<BotUser> cache;

    private final MessageSender messageSender;

    public SendMessageService(MessageSender messageSender) {
        this.messageSender = messageSender;
    }

    public void keyBord(Message message, Update update) {
        SendMessage sendMessage = new SendMessage();
        Message massage = update.getMessage();
        sendMessage.setChatId(String.valueOf(message.getChatId()));

        var markup = new ReplyKeyboardMarkup();
        var keyboardRows = new ArrayList<KeyboardRow>();
        KeyboardRow row1 = new KeyboardRow();
        KeyboardRow row2 = new KeyboardRow();
        KeyboardRow row3 = new KeyboardRow();
        GenerateNewPassword generateNewPassword = new GenerateNewPassword();
        RowsActions row1action = new RowsActions(messageSender);
        //   row1.add(KeyboardButton.builder().text("Generate new password"))
        row1.add(KeyboardButton.builder()
                        .text("Generate new password")

                .build());
        row2.add(KeyboardButton.builder().text("Save last password as (soon)")
                .requestContact(true)
                .build());
        row3.add(KeyboardButton.builder()
                .requestLocation(true)
                .text("Nothing here")
                .build());
        keyboardRows.add(row1);
        keyboardRows.add(row2);
        keyboardRows.add(row3);
        markup.setKeyboard(keyboardRows);
        markup.setResizeKeyboard(true);
        markup.setOneTimeKeyboard(true);
        if (message.getText().equals("Generate new password")){
           // row1action.row1Action(message, update);
            if(update.hasMessage()){
                message = update.getMessage();
                sendMessage.setText(message.getText());
                System.out.println(message.getText());
                messageSender.sendMessage(sendMessage);
            }
        }
        //sendMessage.setReplyMarkup(markup);
        // messageSender.sendMessage(sendMessage);
    }
}
