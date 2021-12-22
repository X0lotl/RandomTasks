package com.Xolotl.services;


import com.Xolotl.backgroundActions.GenerateNewPassword;
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
    row1.add("Generate new password");
    GenerateNewPassword generateNewPassword = new GenerateNewPassword();
    sendMessage.setText("Please enter your new password length");
    messageSender.sendMessage(sendMessage);
     if (update.hasMessage()) {
      int passLength = Integer.parseInt(message.getText());
      String pass = generateNewPassword.generatePassword(passLength); // pass - will be used for saving last password
      sendMessage.setText(pass);
      messageSender.sendMessage(sendMessage);
     }
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
    //sendMessage.setReplyMarkup(markup);
   // messageSender.sendMessage(sendMessage);
  }
}
