package ru.nuykin.diplom.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.nuykin.diplom.service.impl.DistributorServiceImpl;
import ru.nuykin.diplom.util.MessageUtils;

@Component
@RequiredArgsConstructor
@Log4j2
public class UpdateProcessor {
    private TelegramBot telegramBot;
    private DistributorServiceImpl distributorService;

    private final MessageUtils messageUtils;


    public void registerBot(TelegramBot telegramBot) {
        this.telegramBot = telegramBot;
    }
    public void registerDistributorService(DistributorServiceImpl distributorService) {
        this.distributorService = distributorService;
    }

    public void processUpdate(Update update) {
        if (update == null) {
            log.error("Received update is null");
            return;
        }

        if (update.hasMessage()) {
            Message message = update.getMessage();
            if (message.hasText()) {
                distributorService.distribute(update);
            } else {
                setUnsupportedMessageTypeView(update);
            }
        } else {
            log.error("Unsupported message type is received: " + update);
        }
    }

    private void setUnsupportedMessageTypeView(Update update) {
        SendMessage sendMessage = messageUtils.generateSendMessageWithText(
                update,
                "Неподдерживаемый тип сообщения!"
        );
        setView(sendMessage);
    }

    public void setView(SendMessage sendMessage) {
        sendMessage.enableHtml(true);
        telegramBot.sendAnswerMessage(sendMessage);
    }
}
