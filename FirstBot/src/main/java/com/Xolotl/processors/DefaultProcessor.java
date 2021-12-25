package com.Xolotl.processors;

import com.Xolotl.handlers.CallbackQueryHandler;
import com.Xolotl.handlers.MessageHandler;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
@Component
public class DefaultProcessor implements Processor {
    private final CallbackQueryHandler callbackQueryHandler;
    private final MessageHandler messageHandler;

    public DefaultProcessor(CallbackQueryHandler callbackQueryHandler, MessageHandler messageHandler) {
        this.callbackQueryHandler = callbackQueryHandler;
        this.messageHandler = messageHandler;
    }

    @Override
    public void executeMessage(Message message) {

    }

    @Override
    public void executeCallBackQuery(CallbackQuery callbackQuery) {

    }
}
