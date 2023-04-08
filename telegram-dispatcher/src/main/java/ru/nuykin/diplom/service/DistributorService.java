package ru.nuykin.diplom.service;

import org.telegram.telegrambots.meta.api.objects.Update;

public interface DistributorService {
    void distribute(Update update);
}

