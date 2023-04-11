package ru.nuykin.diplom.model;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MyCallbackQuery {
    public enum type { FEEDBACK, SIMILAR_NEWS };

    private type callbackType;
    private Long chatId;
    private Long userId;
    private Long newsId;
    private Long messageId;
    private String keycloakEmail;
    private String otherData;

    @Override
    public String toString() {
        return callbackType + ","
                + chatId + ","
                + userId + ","
                + newsId + ","
                + messageId + ","
                + keycloakEmail + ","
                + otherData;
    }

    public static MyCallbackQuery fromString(String str) {
        String[] parts = str.split(",");
        type callbackType = type.valueOf(parts[0]);
        Long chatId = Long.parseLong(parts[1]);
        Long userId = Long.parseLong(parts[2]);
        Long newsId = Long.parseLong(parts[3]);
        Long messageId = parts[4].equals("null") ? null : Long.parseLong(parts[4]);
        String keycloakEmail = parts[5];
        String otherData = parts[6].equals("null") ? null : parts[6];
        return new MyCallbackQuery(callbackType, chatId, userId, newsId, messageId, keycloakEmail, otherData);
    }
}