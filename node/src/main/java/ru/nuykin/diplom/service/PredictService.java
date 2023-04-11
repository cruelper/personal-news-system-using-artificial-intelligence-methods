package ru.nuykin.diplom.service;

import org.telegram.telegrambots.meta.api.objects.Update;
import ru.nuykin.diplom.model.MyUpdateQuery;

public interface PredictService {
    void predict(MyUpdateQuery update);
}

