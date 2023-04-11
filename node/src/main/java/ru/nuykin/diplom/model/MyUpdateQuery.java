package ru.nuykin.diplom.model;

import lombok.Data;
import org.telegram.telegrambots.meta.api.objects.Update;

@Data
public class MyUpdateQuery extends Update {
    String keycloakEmail;
    public MyUpdateQuery(Update update, String keycloakEmail) {
        this.keycloakEmail = keycloakEmail;

        this.setUpdateId(update.getUpdateId());
        this.setMessage(update.getMessage());
        this.setInlineQuery(update.getInlineQuery());
        this.setChosenInlineQuery(update.getChosenInlineQuery());
        this.setCallbackQuery(update.getCallbackQuery());
        this.setEditedMessage(update.getEditedMessage());
        this.setChannelPost(update.getChannelPost());
        this.setEditedChannelPost(update.getEditedChannelPost());
        this.setShippingQuery(update.getShippingQuery());
        this.setPreCheckoutQuery(update.getPreCheckoutQuery());
        this.setPoll(update.getPoll());
        this.setPollAnswer(update.getPollAnswer());
        this.setMyChatMember(update.getMyChatMember());
        this.setChatMember(update.getChatMember());
        this.setChatJoinRequest(update.getChatJoinRequest());
    }
}