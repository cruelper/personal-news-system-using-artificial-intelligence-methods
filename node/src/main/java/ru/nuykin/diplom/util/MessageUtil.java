package ru.nuykin.diplom.util;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import ru.nuykin.diplom.model.MyCallbackQuery;

import java.util.ArrayList;
import java.util.List;

public class MessageUtil {
    public static InlineKeyboardMarkup generateRatingButtonsAndSimilarButtonMarkup(
            Long chatId, Long userId, Long newsId, String keycloakName
    ) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

        List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();

        for (int i = 1; i <= 5; i++) {
            InlineKeyboardButton inlineKeyboardButton = new InlineKeyboardButton();
            inlineKeyboardButton.setText(String.valueOf(i));
            inlineKeyboardButton.setCallbackData(
                    MyCallbackQuery.type.FEEDBACK + "," +
                            chatId.toString() + "," +
                            userId.toString() + "," +
                            newsId.toString() + "," +
                            null + "," +
                            keycloakName + "," +
                            null
            );

            keyboardButtonsRow1.add(inlineKeyboardButton);
        }

        List<InlineKeyboardButton> keyboardButtonsRow2 = new ArrayList<>();
        keyboardButtonsRow2.add(
                InlineKeyboardButton.builder()
                        .text("Показать похожие")
                        .callbackData(
                                MyCallbackQuery.type.SIMILAR_NEWS + "," +
                                        chatId.toString() + "," +
                                        userId.toString() + "," +
                                        newsId.toString() + "," +
                                        null + "," +
                                        keycloakName + "," +
                                        null
                        )
                        .build()

        );

        inlineKeyboardMarkup.setKeyboard(List.of(
                keyboardButtonsRow1,
                keyboardButtonsRow2
        ));

        return inlineKeyboardMarkup;
    }
}
