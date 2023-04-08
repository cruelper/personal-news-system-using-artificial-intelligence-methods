package ru.nuykin.diplom.service.impl;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import ru.nuykin.diplom.component.ActiveUserStorage;
import ru.nuykin.diplom.config.kafka.KafkaTopic;
import ru.nuykin.diplom.controller.UpdateProcessor;
import ru.nuykin.diplom.service.DistributorService;
import ru.nuykin.diplom.util.UserUtils;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DistributorServiceImpl implements DistributorService {
    private final ProducerServiceImpl producerService;

    private final ActiveUserStorage activeUserStorage;

    private final UserUtils userUtils;

    private final UpdateProcessor updateProcessor;

    @PostConstruct
    public void init() {
        updateProcessor.registerDistributorService(this);
        activeUserStorage.registerDistributorService(this);
    }

    @Override
    public void distribute(Update update) {
        var chatId = update.getMessage().getChatId();
        var userId = update.getMessage().getFrom().getId();
        var text = update.getMessage().getText();

        if (text.equals("/start")) distributeStartMessage(chatId);
        else {
            if (!activeUserStorage.isUserAuthorize(userId)) distributeLogInMessage(chatId, userId);
            else {
                switch (text) {
                    case "/newspack" -> {
                        distributeGetNewsMessage(update);
                    }
                    case "/feedback" -> {
                        distributeFeedbackMessage(update);
                    }
                    case "/help" -> {
                        distributeHelpMessage(chatId);
                    }
                    case "/nottouch" -> {
                        distributeUDieMessage(chatId);
                    }
                    case "/logout" -> {
                        distributeLogOutMessage(chatId, userId);
                    }
                    default -> distributeUnknownMessage(chatId);
                }
            }
        }
    }

    public void distributeLogInSuccessMessage(Long chatId) {
        var sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);

        sendMessage.setText("Вы успешно авторизованы");

        updateProcessor.setView(sendMessage);
    }

    void distributeLogInMessage(Long chatId, Long userId) {
        var sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);

        var code = activeUserStorage.startAuthorize(userId, chatId);
        var url = userUtils.generateAuthorizationUrl(code);

        sendMessage.setText("Для продолжения необходимо авторизоваться: " + url);

        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
        List<InlineKeyboardButton> rowInline = new ArrayList<>();

        InlineKeyboardButton inlineKeyboardButton = new InlineKeyboardButton();
        inlineKeyboardButton.setUrl("https://www.google.com/");
        inlineKeyboardButton.setText("Перейти на страницу авторизации");
//        inlineKeyboardButton.setCallbackData("Call back data");

        rowInline.add(inlineKeyboardButton);

        rowsInline.add(rowInline);
        markupInline.setKeyboard(rowsInline);

        sendMessage.setReplyMarkup(markupInline);

        updateProcessor.setView(sendMessage);
    }

    void distributeStartMessage(Long chatId) {
        var sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(
                "Привет! \n Напиши /help чтобы узнать, что я могу"
        );

        updateProcessor.setView(sendMessage);
    }

    void distributeGetNewsMessage(Update update) {
        producerService.produce(KafkaTopic.PREDICT_REQUEST_TOPIC, update);
    }

    void distributeFeedbackMessage(Update update) {
        producerService.produce(KafkaTopic.FEEDBACK_REQUEST_TOPIC, update);
    }

    void distributeHelpMessage(Long chatId) {
        var sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(
                """
                        Справка:\s
                        /newspack - получить подборку новостей
                        /feedback - оценить подборку новостей
                        /help - доступные команды
                        /nottouch - не нажимать
                        /logout - выйти из аккаунта
                """
        );

        updateProcessor.setView(sendMessage);
    }

    void distributeUnknownMessage(Long chatId) {
        var sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(
                """
                        Я тебя не понимаю!!
                """
        );

        updateProcessor.setView(sendMessage);

        distributeHelpMessage(chatId);
    }

    void distributeUDieMessage(Long chatId) {
        var sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(
                """
                        Тебе ж сказали не нажимать
                        Ахахах
                """
        );

//        IntStream.range(0, 100).forEach(i ->
                updateProcessor.setView(sendMessage);
//        );
        sendMessage.setText(
                """
                        Нажмешь еще раз?
                """
        );
        updateProcessor.setView(sendMessage);
    }

    void distributeLogOutMessage(Long chatId, Long userId) {
        activeUserStorage.logOutUser(userId);

        var sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(
                """
                        Выход из аккаунта выполнен.
                """
        );

        updateProcessor.setView(sendMessage);
    }
}

