package ru.nuykin.diplom.exception;

public class NewsSourceNotFoundException extends RuntimeException  {
    public NewsSourceNotFoundException () {
        super("Источник новостей с указанным id не найден");
    }
}