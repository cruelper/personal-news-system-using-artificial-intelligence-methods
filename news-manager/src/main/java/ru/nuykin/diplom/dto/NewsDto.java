package ru.nuykin.diplom.dto;

import lombok.*;

import java.util.Date;

@Data
public class NewsDto {
    Long idNewsSource;
    String title;
    String url;
    Date date;
}
