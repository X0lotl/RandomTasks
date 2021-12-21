package com.Xolotl.FirstBot.services;


import com.Xolotl.FirstBot.MessageSender;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

@Service
public class SendMessageService {

    MessageSender messageSender = new MessageSender();

    public SendMessageService(MessageSender messageSender) {
        this.messageSender = messageSender;
    }

    public void defaultMessage(Message message) {
        var ms1 = SendMessage.builder()
                .text("<b>Bold</b>" +
                        " <i>italic</i>" +
                        " <code>mono</code>" +
                        " <a> href=\"google.com\">Google</a>")
                .parseMode("HTML")
                .chatId(String.valueOf(message.getChatId()))
                .build();

        messageSender.sendMessage(ms1);
    }
}
